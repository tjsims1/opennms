/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2019 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2019 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opennms.core.test.OpenNMSJUnit4ClassRunner;
import org.opennms.core.test.db.MockDatabase;
import org.opennms.core.test.db.annotations.JUnitTemporaryDatabase;
import org.opennms.netmgt.config.poller.outages.Outages;
import org.opennms.netmgt.config.ro.OutagesConfigReadOnlyDao;
import org.opennms.netmgt.config.ro.ThreshdConfigReadOnlyDao;
import org.opennms.netmgt.config.ro.ThresholdsConfigReadOnlyDao;
import org.opennms.netmgt.config.threshd.ThreshdConfiguration;
import org.opennms.netmgt.config.threshd.ThresholdingConfig;
import org.opennms.test.JUnitConfigurationEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(OpenNMSJUnit4ClassRunner.class)
@ContextConfiguration(locations={
        "classpath:/META-INF/opennms/applicationContext-commonConfigs.xml",
        "classpath:/META-INF/opennms/applicationContext-minimal-conf.xml",
        "classpath:/META-INF/opennms/applicationContext-soa.xml",
        "classpath:/META-INF/opennms/applicationContext-dao.xml",
        "classpath*:/META-INF/opennms/component-dao.xml",
        "classpath:/META-INF/opennms/applicationContext-daemon.xml",
        "classpath:/META-INF/opennms/mockEventIpcManager.xml",
        "classpath:/META-INF/opennms/applicationContext-thresholding.xml",
        "classpath:/META-INF/opennms/applicationContext-proxy-snmp.xml",
        "classpath:/META-INF/opennms/applicationContext-rpc-collector.xml"
})
@JUnitConfigurationEnvironment(systemProperties={// We don't need a real pinger here
        "org.opennms.netmgt.icmp.pingerClass=org.opennms.netmgt.icmp.NullPinger"})
@JUnitTemporaryDatabase(tempDbClass = MockDatabase.class, reuseDatabase = false)
@Transactional
public class ReadOnlyConfigDaoIT {

    @Autowired
    PollOutagesConfigFactory outagesConfig;
    
    @Autowired
    OutagesConfigReadOnlyDao outagesReadOnly;

    @Autowired
    ThreshdConfigFactory threshdConfig;

    @Autowired
    ThreshdConfigReadOnlyDao threshdReadOnly;

    @Autowired
    ThresholdsConfigFactory thresholdsConfig;
    
    @Autowired
    ThresholdsConfigReadOnlyDao thresholdsReadOnly;

    @Before
    public void setup() {
        // System.setProperty("opennms.home", getClass().getResource("resources").getFile());
    }


    @Test
    public void testOutages() throws IOException {
        /*FIXME - below updates config - but does not save new effective value
        File outagesFile = new File("src/test/resources/poll-outages.xml");
        Resource configResource = new FileSystemResource(outagesFile.getPath());
        outagesConfig.setConfigResource(configResource);
        outagesConfig.afterPropertiesSet();*/
        // FIXME - this reads from base assembly which is empty outage config
        Outages outages = outagesReadOnly.getConfig();
        assertNotNull(outages);
        assertEquals(outagesConfig.getOutages(), outages.getOutages());
    }

    @Test
    public void testThreshd() throws IOException {
        ThreshdConfiguration threshdConfiguration = threshdReadOnly.getConfig();
        assertNotNull(threshdConfiguration);
        assertEquals(threshdConfig.getConfiguration(), threshdConfiguration);
    }

    @Test
    public void testThresholds() throws IOException {
        ThresholdingConfig thresholdingConfig = thresholdsReadOnly.getConfig();
        assertNotNull(thresholdingConfig);
        assertEquals(thresholdsConfig.getConfig(), thresholdingConfig);
    }

}
