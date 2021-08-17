/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2021 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2021 The OpenNMS Group, Inc.
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

package org.opennms.mbean.stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class MBeanStatsCollector implements BundleActivator {
    private static final String PKG_NAME = "org.opennms.netmgt.eventd";
    private boolean stop = false;
    private int interval = 5; //minutes

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        stop = false;
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName objName = new ObjectName(PKG_NAME + ":name=eventlogs.process*");
        Set<ObjectName> mbeanSet = server.queryNames(objName, null);

        while (!stop) {
            StringBuilder sb = new StringBuilder();
            sb.append(new Date()).append("\n");
            for (ObjectName mbeanName : mbeanSet) {
                sb.append("\tStats for MBean " + mbeanName.getCanonicalName() + ":\n\t");
                MBeanInfo info = server.getMBeanInfo(mbeanName);
                MBeanAttributeInfo[] attributes = info.getAttributes();
                String[] attNames = Arrays.stream(attributes).map(i -> i.getName()).toArray(String[]::new);
                List<Attribute> attList = server.getAttributes(mbeanName, attNames).asList();
                for(Attribute att: attList) {
                    sb.append(att.getName() + ",");
                }
                sb.append("\n\t");
                for(Attribute att: attList) {
                    sb.append(att.getValue() + ",");
                }
                sb.append("\n");
            }
            sb.append("\n\n");
            writeData(sb.toString());
            TimeUnit.MINUTES.sleep(interval);
        }
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        stop = true;
    }

    private void writeData(String data) throws IOException {
        String fileName = System.getenv("OPENNMS_HOME") + "/logs/mbean_stats.txt";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, true);
        Writer output = new BufferedWriter(writer);
        output.append(data);
        output.close();
    }
}
