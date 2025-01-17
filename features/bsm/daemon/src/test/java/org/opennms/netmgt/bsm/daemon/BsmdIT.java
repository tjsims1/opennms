/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2009-2015 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2015 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.bsm.daemon;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.opennms.core.profiler.ProfilerAspect.humanReadable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opennms.core.profiler.Timer;
import org.opennms.core.spring.BeanUtils;
import org.opennms.core.test.OpenNMSJUnit4ClassRunner;
import org.opennms.core.test.db.annotations.JUnitTemporaryDatabase;
import org.opennms.netmgt.bsm.karaf.shell.GenerateHierarchiesShellCommand;
import org.opennms.netmgt.bsm.persistence.api.BusinessServiceDao;
import org.opennms.netmgt.bsm.persistence.api.BusinessServiceEntity;
import org.opennms.netmgt.bsm.persistence.api.functions.map.IdentityEntity;
import org.opennms.netmgt.bsm.persistence.api.functions.reduce.HighestSeverityEntity;
import org.opennms.netmgt.bsm.service.BusinessServiceManager;
import org.opennms.netmgt.bsm.service.internal.BusinessServiceImpl;
import org.opennms.netmgt.bsm.service.model.BusinessService;
import org.opennms.netmgt.bsm.service.model.Status;
import org.opennms.netmgt.config.DefaultEventConfDao;
import org.opennms.netmgt.dao.DatabasePopulator;
import org.opennms.netmgt.dao.api.AlarmDao;
import org.opennms.netmgt.dao.api.ApplicationDao;
import org.opennms.netmgt.dao.api.DistPollerDao;
import org.opennms.netmgt.dao.mock.EventAnticipator;
import org.opennms.netmgt.dao.mock.MockEventIpcManager;
import org.opennms.netmgt.dao.util.ReductionKeyHelper;
import org.opennms.netmgt.events.api.EventConstants;
import org.opennms.netmgt.model.OnmsAlarm;
import org.opennms.netmgt.model.OnmsApplication;
import org.opennms.netmgt.model.OnmsMonitoredService;
import org.opennms.netmgt.model.OnmsSeverity;
import org.opennms.netmgt.model.events.EventBuilder;
import org.opennms.netmgt.xml.event.Event;
import org.opennms.test.JUnitConfigurationEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionOperations;

import com.google.common.collect.Lists;

@RunWith(OpenNMSJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:/META-INF/opennms/applicationContext-soa.xml",
        "classpath:/META-INF/opennms/applicationContext-dao.xml",
        "classpath:/META-INF/opennms/applicationContext-mockConfigManager.xml",
        "classpath:/META-INF/opennms/applicationContext-commonConfigs.xml",
        "classpath:/META-INF/opennms/applicationContext-minimal-conf.xml",
        "classpath*:/META-INF/opennms/component-dao.xml",
        "classpath*:/META-INF/opennms/component-service.xml",
        "classpath:/META-INF/opennms/applicationContext-daemon.xml",
        "classpath:/META-INF/opennms/mockEventIpcManager.xml",
        "classpath:/META-INF/opennms/applicationContext-databasePopulator.xml",
        "classpath:/META-INF/opennms/applicationContext-bsmd.xml"
})
@JUnitConfigurationEnvironment
@JUnitTemporaryDatabase(reuseDatabase = false)
public class BsmdIT {

    private static final ArrayList<String> REQUIRED_EVENT_UEIS = Lists.newArrayList(
            EventConstants.NODE_LOST_SERVICE_EVENT_UEI,
            EventConstants.NODE_DOWN_EVENT_UEI,
            EventConstants.INTERFACE_DOWN_EVENT_UEI);

    @Autowired
    private DistPollerDao m_distPollerDao;

    @Autowired
    private AlarmDao m_alarmDao;

    @Autowired
    private ApplicationDao m_applicationDao;

    @Autowired
    private DatabasePopulator m_databasePopulator;

    @Autowired
    private BusinessServiceDao m_businessServiceDao;

    @Autowired
    private MockEventIpcManager m_eventMgr;

    @Autowired
    private Bsmd m_bsmd;

    @Autowired
    private BusinessServiceManager businessServiceManager;

    @Autowired
    private TransactionOperations template;

    @Before
    public void setUp() throws Exception {
        BeanUtils.assertAutowiring(this);
        System.setProperty(Bsmd.POLL_INTERVAL_KEY, String.valueOf(Bsmd.DEFAULT_POLL_INTERVAL));

        // We don't have a full blown event configuration, so don't validate these during the integration tests
        m_bsmd.setVerifyReductionKeys(false);
        // Replace the default eventIpcManager with our mock
        m_bsmd.setEventIpcManager(m_eventMgr);

        m_databasePopulator.populateDatabase();
    }

    @After
    public void tearDown() throws Exception {
        m_bsmd.destroy();
    }

    /**
     * Verifies that the daemon generates events when the operational status
     * of a Business Service is changed.
     *
     * Also verifies that the generate events include parameters which map
     * to the business services attributes.
     *
     * @throws Exception
     */
    @Test
    @Transactional
    public void canSendEventsOnOperationalStatusChanged() throws Exception {
        // Create a business service
        BusinessServiceEntity simpleBs = createSimpleBusinessService();

        // Start the daemon
        m_bsmd.start();

        // Expect a statusChanged event
        EventBuilder ebldr = new EventBuilder(EventConstants.BUSINESS_SERVICE_OPERATIONAL_STATUS_CHANGED_UEI, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(ebldr.getEvent());

        // Expect a serviceProblem event
        ebldr = new EventBuilder(EventConstants.BUSINESS_SERVICE_PROBLEM_UEI, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(ebldr.getEvent());

        // Create the alarm
        OnmsAlarm alarm = createAlarm();
        m_alarmDao.save(alarm);
        m_bsmd.handleNewOrUpdatedAlarm(alarm);

        // Verify expectations
        Collection<Event> stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        verifyParametersOnAnticipatedEventsReceived(m_eventMgr.getEventAnticipator(), simpleBs.getId());

        // Expect a statusChanged event
        ebldr = new EventBuilder(EventConstants.BUSINESS_SERVICE_OPERATIONAL_STATUS_CHANGED_UEI, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(ebldr.getEvent());

        // Expect a serviceProblemResolved event
        ebldr = new EventBuilder(EventConstants.BUSINESS_SERVICE_PROBLEM_RESOLVED_UEI, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(ebldr.getEvent());

        // Clear the alarm
        alarm.setSeverity(OnmsSeverity.CLEARED);
        m_bsmd.handleNewOrUpdatedAlarm(alarm);

        // Verify expectations
        stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        verifyParametersOnAnticipatedEventsReceived(m_eventMgr.getEventAnticipator(), simpleBs.getId());

        // Verify that rootCause is set
        final Optional<Event> event = m_eventMgr.getEventAnticipator().getAnticipatedEventsReceived().stream()
                .filter(e -> e.getUei().equals(EventConstants.BUSINESS_SERVICE_PROBLEM_UEI))
                .findFirst();

        assertThat(event.get().getParm("rootCause").getValue().getContent(), is(not(isEmptyOrNullString())));
    }

    private static void verifyParametersOnAnticipatedEventsReceived(EventAnticipator anticipator, Long businessServiceId) {
        for (Event e : anticipator.getAnticipatedEventsReceived()) {
            // The service id should always be set
            assertEquals(e.getParm(EventConstants.PARM_BUSINESS_SERVICE_ID).getValue().getContent(), businessServiceId.toString());
            // Services parameters should also be included
            assertEquals(e.getParm("my-attr-key").getValue().getContent(), "my-attr-value");
        }
    }

    /**
     * Verifies that a reload of the Bsmd works as expected.
     */
    @Test
    @Transactional
    public void verifyReloadBsmd() throws Exception {
        BusinessServiceEntity businessService1 = createBusinessService("service1");
        m_bsmd.start();
        Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService1)));

        // verify reload of business services works when event is send
        BusinessServiceEntity businessService2 = createBusinessService("service2");
        Assert.assertNull(m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));
        reloadBsmd();
        Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));
    }

    public void verifyReloadBySendingEventUei(final String uei) throws Exception {
        final BusinessServiceEntity businessService1 = createBusinessService("service1");
        m_bsmd.start();
        Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService1)));

        final BusinessServiceEntity businessService2 = createBusinessService("service2");
        Assert.assertNull(m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));

        final EventBuilder eventBuilder = new EventBuilder(uei, "test")
                .setNodeid(m_databasePopulator.getNode1().getId())
                .setInterface(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getIpAddress())
                .setService(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getMonitoredServices().iterator().next().getServiceName());

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        await().atMost(5, SECONDS).until(() -> m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)), equalTo(Status.NORMAL));
    }

    @Test
    public void verifyDelayedReload() throws Exception {
        final BusinessServiceEntity businessService1 = createBusinessService("service1");
        m_bsmd.start();
        Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService1)));

        final BusinessServiceEntity businessService2 = createBusinessService("service2");
        Assert.assertNull(m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));

        final EventBuilder eventBuilder = new EventBuilder(EventConstants.SERVICE_DELETED_EVENT_UEI, "test")
                .setNodeid(m_databasePopulator.getNode1().getId())
                .setInterface(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getIpAddress())
                .setService(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getMonitoredServices().iterator().next().getServiceName());

        for (int i = 0; i < 5; i++) {
            m_eventMgr.sendNow(eventBuilder.getEvent(), true);
            Thread.sleep(Bsmd.RELOAD_DELAY / 2);
            Assert.assertNull(m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));
        }

        await().atMost(5, SECONDS).until(() -> m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)), equalTo(Status.NORMAL));
    }

    @Test
    public void verifyReloadByDeletionOfService() throws Exception {
        verifyReloadBySendingEventUei(EventConstants.SERVICE_DELETED_EVENT_UEI);
    }

    @Test
    public void verifyReloadByDeletionOfInterface() throws Exception {
        verifyReloadBySendingEventUei(EventConstants.INTERFACE_DELETED_EVENT_UEI);
    }

    @Test
    public void verifyReloadByDeletionOfNode() throws Exception {
        verifyReloadBySendingEventUei(EventConstants.NODE_DELETED_EVENT_UEI);
    }

    @Test
    public void verifyReloadByDeletionOfApplication() throws Exception {
        final BusinessServiceEntity businessService1 = createBusinessServiceWithApplicationEdge("service1");
        m_bsmd.start();
        Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService1)));

        OnmsApplication onmsApplication = m_applicationDao.findByName("myApp");

        final BusinessServiceEntity businessService2 = createBusinessService("service2");
        Assert.assertNull(m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)));

        final EventBuilder eventBuilder = new EventBuilder(EventConstants.APPLICATION_DELETED_EVENT_UEI, "test")
                .setParam("applicationId", onmsApplication.getId())
                .setParam("applicationName", "myApp");

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        await().atMost(5, SECONDS).until(() -> m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(businessService2)), equalTo(Status.NORMAL));
    }

    @Test
    public void verifyEventsForServiceDeletion() throws Exception {
        EventBuilder eventBuilder;
        createComplexTree();
        m_bsmd.start();

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.SERVICE_DELETED_EVENT_UEI, "test")
                .setNodeid(m_databasePopulator.getNode1().getId())
                .setInterface(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getIpAddress())
                .setService(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getMonitoredServices().iterator().next().getServiceName());

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        final Collection<Event> stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        assertThat(m_eventMgr.getEventAnticipator().getAnticipatedEventsReceived().stream().map(e -> e.getParm("businessServiceName").getValue().getContent()).collect(Collectors.toSet()), containsInAnyOrder("BS2", "BS4"));
    }

    @Test
    public void verifyEventsForInterfaceDeletion() throws Exception {
        EventBuilder eventBuilder;
        createComplexTree();
        m_bsmd.start();

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.INTERFACE_DELETED_EVENT_UEI, "test")
                .setNodeid(m_databasePopulator.getNode1().getId())
                .setInterface(m_databasePopulator.getNode1().getIpInterfaces().iterator().next().getIpAddress());

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        final Collection<Event> stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        assertThat(m_eventMgr.getEventAnticipator().getAnticipatedEventsReceived().stream().map(e -> e.getParm("businessServiceName").getValue().getContent()).collect(Collectors.toSet()), containsInAnyOrder("BS1", "BS2"));
    }

    @Test
    public void verifyEventsForNodeDeletion() throws Exception {
        EventBuilder eventBuilder;
        createComplexTree();
        m_bsmd.start();

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.NODE_DELETED_EVENT_UEI, "test")
                .setNodeid(m_databasePopulator.getNode1().getId());

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        final Collection<Event> stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        assertThat(m_eventMgr.getEventAnticipator().getAnticipatedEventsReceived().stream().map(e -> e.getParm("businessServiceName").getValue().getContent()).collect(Collectors.toSet()), containsInAnyOrder("BS2", "BS3"));
    }

    @Test
    public void verifyEventsForApplicationDeletion() throws Exception {
        EventBuilder eventBuilder;
        createComplexTree();
        m_bsmd.start();

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.BUSINESS_SERVICE_GRAPH_INVALIDATED, "test");
        m_eventMgr.getEventAnticipator().anticipateEvent(eventBuilder.getEvent());

        eventBuilder = new EventBuilder(EventConstants.APPLICATION_DELETED_EVENT_UEI, "test")
                .setParam("applicationId", m_applicationDao.findByName("A1").getId())
                .setParam("applicationName", "A1");

        m_eventMgr.sendNow(eventBuilder.getEvent(), true);

        final Collection<Event> stillWaitingFor = m_eventMgr.getEventAnticipator().waitForAnticipated(5000);
        assertTrue("Expected events not forthcoming " + stillWaitingFor, stillWaitingFor.isEmpty());
        assertThat(m_eventMgr.getEventAnticipator().getAnticipatedEventsReceived().stream().map(e -> e.getParm("businessServiceName").getValue().getContent()).collect(Collectors.toSet()), containsInAnyOrder("BS1", "BS2"));
    }

    /**
     * Verifies that the state is properly updated when handling alarm snapshots from the lifecycle API.
     */
    @Test
    public void verifyAlarmSnapshotHandling() throws Exception {
        BusinessServiceEntity simpleBs = createSimpleBusinessService();
        m_bsmd.start();

        // Create an alarm
        final AtomicReference<OnmsAlarm> alarmRef = new AtomicReference<>();
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(simpleBs)));
                OnmsAlarm alarm = createAlarm();
                m_alarmDao.save(alarm);
                alarmRef.set(alarm);
                m_alarmDao.flush();
                Assert.assertEquals(Status.NORMAL, m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(simpleBs)));
            }
        });

        // Issue a snapshot callback
        template.execute(new TransactionCallbackWithoutResult() {
             @Override
             protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                 m_bsmd.handleAlarmSnapshot(m_alarmDao.findAll());
             }
         });

        // Wait for the business service status to be updated
        await().atMost(20, SECONDS).until(() -> m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(simpleBs)), equalTo(Status.CRITICAL));

        // Now delete alarm
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                m_alarmDao.delete(alarmRef.get());
                m_alarmDao.flush();
            }
        });

        // Issue a snapshot callback
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                m_bsmd.handleAlarmSnapshot(m_alarmDao.findAll());
            }
        });

        // Wait for the business service status to be updated
        await().atMost(20, SECONDS).until(() -> m_bsmd.getBusinessServiceStateMachine().getOperationalStatus(wrap(simpleBs)), equalTo(Status.NORMAL));
    }

    /**
     * Verify that Bsmd can start within a reasonable amount of time when 20k business services exist.
     */
    @Test
    @Transactional
    public void verifyStartupTime() throws Exception {
        // generate test hierarchy
        GenerateHierarchiesShellCommand shellCommand = new GenerateHierarchiesShellCommand();
        shellCommand.businessServiceManager = businessServiceManager;
        shellCommand.setNumServices(200 * 100); // 200 hierarchies
        shellCommand.setDepth(100); // 100 services each
        shellCommand.execute();
        m_businessServiceDao.flush();

        // Measure startup time
        Timer timer = new Timer();
        timer.start();
        m_bsmd.start();
        long diff = timer.stop();
        Assert.assertTrue("Bsmd took " + humanReadable(diff) + " to start but only 30 seconds are considered reasonable. Please optimize startup time.",
                diff <= 30 * 1000 /* 30 seconds */);
    }

    @Test
    public void verifyStartupWithoutRequiredEventData() throws Exception {
        // Verify Pre-Condition
        REQUIRED_EVENT_UEIS.forEach(eachUei -> {
            List<org.opennms.netmgt.xml.eventconf.Event> events = m_bsmd.getEventConfDao().getEvents(eachUei);
            Assert.assertTrue("Expected null or empty events with uei '" + eachUei + "'", events == null || events.isEmpty());
        });

        // Verify start up
        m_bsmd.setVerifyReductionKeys(true);
        m_bsmd.start();
    }

    @Test
    public void verifyStartupWithoutAlarmData() throws Exception {
        // Load custom events
        DefaultEventConfDao eventConfDao = new DefaultEventConfDao();
        eventConfDao.setConfigResource(new ClassPathResource("/eventconf.xml"));
        eventConfDao.afterPropertiesSet();

        // Remove Alarm Data
        REQUIRED_EVENT_UEIS.forEach(eventUei -> eventConfDao.getEvents(eventUei).get(0).setAlarmData(null));

        // Verify that the alarm data is actually null
        m_bsmd.setEventConfDao(eventConfDao);
        REQUIRED_EVENT_UEIS.forEach(eventUei -> {
            Assert.assertEquals(1, m_bsmd.getEventConfDao().getEvents(eventUei).size());
            Assert.assertNull(m_bsmd.getEventConfDao().getEvents(eventUei).get(0).getAlarmData());
        });

        // Verify start up with null alarm data

        m_bsmd.setVerifyReductionKeys(true);
        m_bsmd.start();
    }

    @Test
    public void verifyStartupWithChangedReductionKey() throws Exception {
        // Load custom events
        DefaultEventConfDao eventConfDao = new DefaultEventConfDao();
        eventConfDao.setConfigResource(new ClassPathResource("/eventconf.xml"));
        eventConfDao.afterPropertiesSet();

        // change reduction key
        REQUIRED_EVENT_UEIS.forEach(uei -> eventConfDao.getEvents(uei).get(0).getAlarmData().setReductionKey("custom"));

        // verify that reduction key actually changed
        REQUIRED_EVENT_UEIS.forEach(uei -> Assert.assertEquals("custom", eventConfDao.getEvents(uei).get(0).getAlarmData().getReductionKey()));

        m_bsmd.setEventConfDao(eventConfDao);
        m_bsmd.setVerifyReductionKeys(true);
        m_bsmd.start();
    }

    private OnmsAlarm createAlarm() {
        OnmsAlarm alarm = new OnmsAlarm();
        alarm.setUei(EventConstants.NODE_LOST_SERVICE_EVENT_UEI);
        alarm.setSeverity(OnmsSeverity.CRITICAL);
        alarm.setAlarmType(OnmsAlarm.PROBLEM_TYPE);
        alarm.setCounter(1);
        alarm.setDistPoller(m_distPollerDao.whoami());
        alarm.setReductionKey(String.format("%s::1:192.168.1.1:ICMP", EventConstants.NODE_LOST_SERVICE_EVENT_UEI));
        return alarm;
    }


    private BusinessServiceEntity createBusinessServiceWithApplicationEdge(String name) {
        final OnmsApplication onmsApplication = new OnmsApplication();

        onmsApplication.setName("myApp");

        final OnmsMonitoredService ipService = m_databasePopulator.getNode1()
                .getIpInterfaces().iterator().next()
                .getMonitoredServices().iterator().next();

        onmsApplication.addMonitoredService(ipService);
        int id = m_applicationDao.save(onmsApplication);
        m_applicationDao.flush();

        final BusinessServiceEntity bs = new BusinessServiceEntity();
        bs.setName(name);
        bs.setReductionFunction(new HighestSeverityEntity());
        bs.setAttribute("my-attr-key", "my-attr-value");
        bs.addApplicationEdge(m_applicationDao.get(id), new IdentityEntity());

        // Persist
        m_businessServiceDao.save(bs);
        m_businessServiceDao.flush();

        return bs;
    }

    private BusinessServiceEntity createBusinessService(String name) {
        BusinessServiceEntity bs = new BusinessServiceEntity();
        bs.setName(name);
        bs.setReductionFunction(new HighestSeverityEntity());
        bs.setAttribute("my-attr-key", "my-attr-value");

        // Grab the first monitored service from node 1
        OnmsMonitoredService ipService = m_databasePopulator.getNode1()
                .getIpInterfaces().iterator().next()
                .getMonitoredServices().iterator().next();
        bs.addIpServiceEdge(ipService, new IdentityEntity());

        // Persist
        m_businessServiceDao.save(bs);
        m_businessServiceDao.flush();

        return bs;
    }

    private String createComplexTree() {

        //             BS1
        //      --------'----------
        //     /    \        /  |  \
        //    BS2    BS3   BS4  A1 R2
        //   / \    / | \   | \
        //  I1  A1 I2 R3 A2 R1 A2
        //
        // A1 -> I2
        // A2 -> I1
        // R2 -> interface of I1
        // R3 -> node of I1
        //
        // R1 = reduction key of I1
        // Deletion of A1: BS1, BS2
        // Deletion of I1: BS2, BS4

        final OnmsMonitoredService i1 = m_databasePopulator.getNode1()
                .getIpInterfaces().iterator().next()
                .getMonitoredServices().iterator().next();

        final OnmsMonitoredService i2 = m_databasePopulator.getNode2()
                .getIpInterfaces().iterator().next()
                .getMonitoredServices().iterator().next();

        final OnmsApplication a1 = new OnmsApplication();
        a1.setName("A1");
        a1.addMonitoredService(i2);
        int a1Id = m_applicationDao.save(a1);
        m_applicationDao.flush();

        final OnmsApplication a2 = new OnmsApplication();
        a2.setName("A2");
        a2.addMonitoredService(i1);
        int a2Id = m_applicationDao.save(a2);
        m_applicationDao.flush();

        BusinessServiceEntity bs2 = new BusinessServiceEntity();
        bs2.setName("BS2");
        bs2.setReductionFunction(new HighestSeverityEntity());
        bs2.setAttribute("my-attr-key", "my-attr-value");
        bs2.addIpServiceEdge(i1, new IdentityEntity());
        bs2.addApplicationEdge(m_applicationDao.get(a1Id), new IdentityEntity());

        final BusinessServiceEntity bs3 = new BusinessServiceEntity();
        bs3.setName("BS3");
        bs3.setReductionFunction(new HighestSeverityEntity());
        bs3.setAttribute("my-attr-key", "my-attr-value");
        bs3.addIpServiceEdge(i2, new IdentityEntity());
        bs3.addApplicationEdge(m_applicationDao.get(a2Id), new IdentityEntity());
        bs3.addReductionKeyEdge(ReductionKeyHelper.getNodeDownReductionKey(i1), new IdentityEntity());

        BusinessServiceEntity bs4 = new BusinessServiceEntity();
        bs4.setName("BS4");
        bs4.setReductionFunction(new HighestSeverityEntity());
        bs4.setAttribute("my-attr-key", "my-attr-value");
        bs4.addReductionKeyEdge(ReductionKeyHelper.getNodeLostServiceReductionKey(i1), new IdentityEntity());
        bs4.addApplicationEdge(m_applicationDao.get(a2Id), new IdentityEntity());

        final BusinessServiceEntity bs1 = new BusinessServiceEntity();
        bs1.setName("BS1");
        bs1.setReductionFunction(new HighestSeverityEntity());
        bs1.setAttribute("my-attr-key", "my-attr-value");
        bs1.addChildServiceEdge(bs2, new IdentityEntity());
        bs1.addChildServiceEdge(bs3, new IdentityEntity());
        bs1.addChildServiceEdge(bs4, new IdentityEntity());
        bs1.addApplicationEdge(m_applicationDao.get(a1Id), new IdentityEntity());
        bs1.addReductionKeyEdge(ReductionKeyHelper.getInterfaceDownReductionKey(i1), new IdentityEntity());

        m_businessServiceDao.save(bs2);
        m_businessServiceDao.save(bs3);
        m_businessServiceDao.save(bs4);
        m_businessServiceDao.save(bs1);
        m_businessServiceDao.flush();

        return ReductionKeyHelper.getNodeLostServiceReductionKey(i1);
    }

    private void deleteAllBusinessServices() {
        m_businessServiceDao.findAll().forEach(bs -> m_businessServiceDao.delete(bs));
        m_businessServiceDao.flush();
    }

    private void reloadBsmd() {
        EventBuilder ebldr = new EventBuilder(EventConstants.RELOAD_DAEMON_CONFIG_UEI, "test");
        ebldr.addParam(EventConstants.PARM_DAEMON_NAME, "bsmd");
        m_eventMgr.sendNow(ebldr.getEvent());
    }

    private BusinessServiceEntity createSimpleBusinessService() {
        return createBusinessService("MyBusinessService");
    }

    private BusinessService wrap(BusinessServiceEntity entity) {
        return new BusinessServiceImpl(businessServiceManager, entity);
    }
}
