/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2013-2014 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2014 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.nb;

import org.opennms.core.utils.InetAddressUtils;
import org.opennms.netmgt.model.OnmsNode;

/**
 * @author <a href="mailto:antonio@opennms.it">Antonio Russo</a>
 */

public class Nms007NetworkBuilder extends NmsNetworkBuilder {

    static {
    try {
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.170"), 507);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("20.0.0.170"), 517);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly170_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.170"), 16);
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.170"), InetAddressUtils.addr("255.255.255.0"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("20.0.0.170"), InetAddressUtils.addr("255.255.255.0"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly170_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.170"), InetAddressUtils.addr("255.255.255.255"));        FireFly170_IF_IFNAME_MAP.put(6, "lo0");

        FireFly170_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly170_IF_IFNAME_MAP.put(512, "lsq-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(512, "lsq-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(513, "mt-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(513, "mt-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(10, "pime");
        FireFly170_IF_IFDESCR_MAP.put(10, "pime");
        FireFly170_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly170_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly170_IF_IFNAME_MAP.put(9, "ipip");
        FireFly170_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly170_IF_IFNAME_MAP.put(502, "st0");
        FireFly170_IF_IFDESCR_MAP.put(502, "st0");
        FireFly170_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly170_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly170_IF_IFNAME_MAP.put(505, "vlan");
        FireFly170_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly170_IF_IFNAME_MAP.put(501, "pp0");
        FireFly170_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly170_IF_IFNAME_MAP.put(8, "gre");
        FireFly170_IF_IFDESCR_MAP.put(8, "gre");
        FireFly170_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly170_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly170_IF_IFNAME_MAP.put(514, "lt-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(514, "lt-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(508, "ge-0/0/1");
        FireFly170_IF_IFDESCR_MAP.put(508, "ge-0/0/1");
        FireFly170_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly170_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly170_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(7, "tap");
        FireFly170_IF_IFDESCR_MAP.put(7, "tap");
        FireFly170_IF_IFNAME_MAP.put(12, "mtun");
        FireFly170_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly170_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly170_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly170_IF_IFNAME_MAP.put(507, "ge-0/0/0.0");
        FireFly170_IF_IFDESCR_MAP.put(507, "ge-0/0/0.0");
        FireFly170_IF_IFNAME_MAP.put(4, "lsi");
        FireFly170_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly170_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly170_IF_IFDESCR_MAP.put(511, "ip-0/0/0");
        FireFly170_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly170_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly170_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly170_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly170_IF_IFNAME_MAP.put(11, "pimd");
        FireFly170_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly170_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly170_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly170_IF_IFNAME_MAP.put(5, "dsc");
        FireFly170_IF_IFDESCR_MAP.put(5, "dsc");

        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.171"), 16);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.171"), 514);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("10.0.1.171"), 517);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly171_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.171"), InetAddressUtils.addr("255.255.255.0"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("10.0.1.171"), InetAddressUtils.addr("255.255.255.0"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly171_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.171"), InetAddressUtils.addr("255.255.255.255"));

        FireFly171_IF_IFNAME_MAP.put(10, "pime");
        FireFly171_IF_IFDESCR_MAP.put(10, "pime");
        FireFly171_IF_IFNAME_MAP.put(6, "lo0");
        FireFly171_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly171_IF_IFNAME_MAP.put(502, "st0");
        FireFly171_IF_IFDESCR_MAP.put(502, "st0");
        FireFly171_IF_IFNAME_MAP.put(513, "lt-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(513, "lt-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly171_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly171_IF_IFNAME_MAP.put(5, "dsc");
        FireFly171_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly171_IF_IFNAME_MAP.put(4, "lsi");
        FireFly171_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly171_IF_IFNAME_MAP.put(514, "ge-0/0/0.0");
        FireFly171_IF_IFDESCR_MAP.put(514, "ge-0/0/0.0");
        FireFly171_IF_IFNAME_MAP.put(510, "ip-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(510, "ip-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(512, "mt-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(512, "mt-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(7, "tap");
        FireFly171_IF_IFDESCR_MAP.put(7, "tap");
        FireFly171_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly171_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly171_IF_IFNAME_MAP.put(511, "lsq-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(511, "lsq-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly171_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly171_IF_IFNAME_MAP.put(11, "pimd");
        FireFly171_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly171_IF_IFNAME_MAP.put(501, "pp0");
        FireFly171_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly171_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly171_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly171_IF_IFNAME_MAP.put(505, "vlan");
        FireFly171_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly171_IF_IFNAME_MAP.put(9, "ipip");
        FireFly171_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly171_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly171_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly171_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(8, "gre");
        FireFly171_IF_IFDESCR_MAP.put(8, "gre");
        FireFly171_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly171_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly171_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly171_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly171_IF_IFNAME_MAP.put(509, "gr-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(509, "gr-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(12, "mtun");
        FireFly171_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly171_IF_IFNAME_MAP.put(508, "sp-0/0/0");
        FireFly171_IF_IFDESCR_MAP.put(508, "sp-0/0/0");
        FireFly171_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly171_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly171_IF_IFNAME_MAP.put(507, "ge-0/0/1");
        FireFly171_IF_IFDESCR_MAP.put(507, "ge-0/0/1");
        
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.172"), 16);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("10.0.1.172"), 517);
        FireFly172_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("10.0.1.172"), InetAddressUtils.addr("255.255.255.0"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly172_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.172"), InetAddressUtils.addr("255.255.255.255"));

        FireFly172_IF_IFNAME_MAP.put(11, "pimd");
        FireFly172_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly172_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly172_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly172_IF_IFNAME_MAP.put(9, "ipip");
        FireFly172_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly172_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly172_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly172_IF_IFNAME_MAP.put(513, "lsq-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(513, "lsq-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(6, "lo0");
        FireFly172_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly172_IF_IFNAME_MAP.put(7, "tap");
        FireFly172_IF_IFDESCR_MAP.put(7, "tap");
        FireFly172_IF_IFNAME_MAP.put(505, "vlan");
        FireFly172_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly172_IF_IFNAME_MAP.put(12, "mtun");
        FireFly172_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly172_IF_IFNAME_MAP.put(4, "lsi");
        FireFly172_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly172_IF_IFNAME_MAP.put(514, "mt-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(514, "mt-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(508, "ge-0/0/0.0");
        FireFly172_IF_IFDESCR_MAP.put(508, "ge-0/0/0.0");
        FireFly172_IF_IFNAME_MAP.put(507, "ge-0/0/1");
        FireFly172_IF_IFDESCR_MAP.put(507, "ge-0/0/1");
        FireFly172_IF_IFNAME_MAP.put(8, "gre");
        FireFly172_IF_IFDESCR_MAP.put(8, "gre");
        FireFly172_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly172_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly172_IF_IFNAME_MAP.put(10, "pime");
        FireFly172_IF_IFDESCR_MAP.put(10, "pime");
        FireFly172_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly172_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly172_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly172_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly172_IF_IFNAME_MAP.put(501, "pp0");
        FireFly172_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly172_IF_IFNAME_MAP.put(5, "dsc");
        FireFly172_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly172_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(511, "ip-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly172_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly172_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly172_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly172_IF_IFNAME_MAP.put(515, "lt-0/0/0");
        FireFly172_IF_IFDESCR_MAP.put(515, "lt-0/0/0");
        FireFly172_IF_IFNAME_MAP.put(502, "st0");
        FireFly172_IF_IFDESCR_MAP.put(502, "st0");
        FireFly172_IF_IFNAME_MAP.put(512, "sp-0/0/0.0");
        FireFly172_IF_IFDESCR_MAP.put(512, "sp-0/0/0.0");
        
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("10.0.2.173"), 507);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.173"), 16);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("10.0.1.173"), 517);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly173_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("10.0.1.173"), InetAddressUtils.addr("255.255.255.0"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("10.0.2.173"), InetAddressUtils.addr("255.255.255.0"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.173"), InetAddressUtils.addr("255.255.255.255"));
        FireFly173_IF_IFNAME_MAP.put(12, "mtun");
        FireFly173_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly173_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly173_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly173_IF_IFNAME_MAP.put(9, "ipip");
        FireFly173_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly173_IF_IFNAME_MAP.put(5, "dsc");
        FireFly173_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly173_IF_IFNAME_MAP.put(501, "pp0");
        FireFly173_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly173_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly173_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly173_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly173_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly173_IF_IFNAME_MAP.put(513, "mt-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(513, "mt-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(502, "st0");
        FireFly173_IF_IFDESCR_MAP.put(502, "st0");
        FireFly173_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly173_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly173_IF_IFNAME_MAP.put(4, "lsi");
        FireFly173_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly173_IF_IFNAME_MAP.put(505, "vlan");
        FireFly173_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly173_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly173_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly173_IF_IFNAME_MAP.put(11, "pimd");
        FireFly173_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly173_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly173_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly173_IF_IFNAME_MAP.put(6, "lo0");
        FireFly173_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly173_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(8, "gre");
        FireFly173_IF_IFDESCR_MAP.put(8, "gre");
        FireFly173_IF_IFNAME_MAP.put(10, "pime");
        FireFly173_IF_IFDESCR_MAP.put(10, "pime");
        FireFly173_IF_IFNAME_MAP.put(512, "lsq-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(512, "lsq-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly173_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly173_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly173_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly173_IF_IFNAME_MAP.put(507, "ge-0/0/0.0");
        FireFly173_IF_IFDESCR_MAP.put(507, "ge-0/0/0.0");
        FireFly173_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(511, "ip-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(7, "tap");
        FireFly173_IF_IFDESCR_MAP.put(7, "tap");
        FireFly173_IF_IFNAME_MAP.put(508, "ge-0/0/1");
        FireFly173_IF_IFDESCR_MAP.put(508, "ge-0/0/1");
        FireFly173_IF_IFNAME_MAP.put(514, "lt-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(514, "lt-0/0/0");
        FireFly173_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly173_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("10.0.2.174"), 507);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.174"), 16);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly174_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("10.0.2.174"), InetAddressUtils.addr("255.255.255.0"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.174"), InetAddressUtils.addr("255.255.255.255"));
        FireFly174_IF_IFNAME_MAP.put(508, "ge-0/0/1");
        FireFly174_IF_IFDESCR_MAP.put(508, "ge-0/0/1");
        FireFly174_IF_IFNAME_MAP.put(505, "vlan");
        FireFly174_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly174_IF_IFNAME_MAP.put(10, "pime");
        FireFly174_IF_IFDESCR_MAP.put(10, "pime");
        FireFly174_IF_IFNAME_MAP.put(11, "pimd");
        FireFly174_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly174_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly174_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly174_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly174_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly174_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly174_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly174_IF_IFNAME_MAP.put(513, "mt-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(513, "mt-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(12, "mtun");
        FireFly174_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly174_IF_IFNAME_MAP.put(6, "lo0");
        FireFly174_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly174_IF_IFNAME_MAP.put(5, "dsc");
        FireFly174_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly174_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(511, "ip-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly174_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly174_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(514, "lt-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(514, "lt-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(8, "gre");
        FireFly174_IF_IFDESCR_MAP.put(8, "gre");
        FireFly174_IF_IFNAME_MAP.put(9, "ipip");
        FireFly174_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly174_IF_IFNAME_MAP.put(512, "lsq-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(512, "lsq-0/0/0");
        FireFly174_IF_IFNAME_MAP.put(4, "lsi");
        FireFly174_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly174_IF_IFNAME_MAP.put(501, "pp0");
        FireFly174_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly174_IF_IFNAME_MAP.put(502, "st0");
        FireFly174_IF_IFDESCR_MAP.put(502, "st0");
        FireFly174_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly174_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly174_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly174_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly174_IF_IFNAME_MAP.put(7, "tap");
        FireFly174_IF_IFDESCR_MAP.put(7, "tap");
        FireFly174_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly174_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly174_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly174_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly174_IF_IFNAME_MAP.put(507, "ge-0/0/0.0");
        FireFly174_IF_IFDESCR_MAP.put(507, "ge-0/0/0.0");
        FireFly174_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly174_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("20.0.0.175"), 517);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("10.0.3.175"), 514);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.175"), 16);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly175_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("10.0.3.175"), InetAddressUtils.addr("255.255.255.0"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("20.0.0.175"), InetAddressUtils.addr("255.255.255.0"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.175"), InetAddressUtils.addr("255.255.255.255"));
        FireFly175_IF_IFNAME_MAP.put(11, "pimd");
        FireFly175_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly175_IF_IFNAME_MAP.put(4, "lsi");
        FireFly175_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly175_IF_IFNAME_MAP.put(7, "tap");
        FireFly175_IF_IFDESCR_MAP.put(7, "tap");
        FireFly175_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly175_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly175_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly175_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly175_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly175_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly175_IF_IFNAME_MAP.put(12, "mtun");
        FireFly175_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly175_IF_IFNAME_MAP.put(512, "mt-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(512, "mt-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(501, "pp0");
        FireFly175_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly175_IF_IFNAME_MAP.put(513, "lt-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(513, "lt-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly175_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly175_IF_IFNAME_MAP.put(6, "lo0");
        FireFly175_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly175_IF_IFNAME_MAP.put(505, "vlan");
        FireFly175_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly175_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly175_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly175_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly175_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly175_IF_IFNAME_MAP.put(9, "ipip");
        FireFly175_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly175_IF_IFNAME_MAP.put(507, "ge-0/0/1");
        FireFly175_IF_IFDESCR_MAP.put(507, "ge-0/0/1");
        FireFly175_IF_IFNAME_MAP.put(502, "st0");
        FireFly175_IF_IFDESCR_MAP.put(502, "st0");
        FireFly175_IF_IFNAME_MAP.put(511, "lsq-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(511, "lsq-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(514, "ge-0/0/0.0");
        FireFly175_IF_IFDESCR_MAP.put(514, "ge-0/0/0.0");
        FireFly175_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly175_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly175_IF_IFNAME_MAP.put(509, "gr-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(509, "gr-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(8, "gre");
        FireFly175_IF_IFDESCR_MAP.put(8, "gre");
        FireFly175_IF_IFNAME_MAP.put(508, "sp-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(508, "sp-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(510, "ip-0/0/0");
        FireFly175_IF_IFDESCR_MAP.put(510, "ip-0/0/0");
        FireFly175_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly175_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly175_IF_IFNAME_MAP.put(10, "pime");
        FireFly175_IF_IFDESCR_MAP.put(10, "pime");
        FireFly175_IF_IFNAME_MAP.put(5, "dsc");
        FireFly175_IF_IFDESCR_MAP.put(5, "dsc");
        
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("10.0.3.176"), 507);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.176"), 16);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("10.0.4.176"), 517);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly176_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("10.0.3.176"), InetAddressUtils.addr("255.255.255.0"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("10.0.4.176"), InetAddressUtils.addr("255.255.255.0"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.176"), InetAddressUtils.addr("255.255.255.255"));
        FireFly176_IF_IFNAME_MAP.put(505, "vlan");
        FireFly176_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly176_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(6, "lo0");
        FireFly176_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly176_IF_IFNAME_MAP.put(507, "ge-0/0/0.0");
        FireFly176_IF_IFDESCR_MAP.put(507, "ge-0/0/0.0");
        FireFly176_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly176_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly176_IF_IFNAME_MAP.put(514, "lt-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(514, "lt-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(5, "dsc");
        FireFly176_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly176_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly176_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly176_IF_IFNAME_MAP.put(513, "mt-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(513, "mt-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly176_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly176_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly176_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly176_IF_IFNAME_MAP.put(508, "ge-0/0/1");
        FireFly176_IF_IFDESCR_MAP.put(508, "ge-0/0/1");
        FireFly176_IF_IFNAME_MAP.put(7, "tap");
        FireFly176_IF_IFDESCR_MAP.put(7, "tap");
        FireFly176_IF_IFNAME_MAP.put(10, "pime");
        FireFly176_IF_IFDESCR_MAP.put(10, "pime");
        FireFly176_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly176_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly176_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly176_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly176_IF_IFNAME_MAP.put(501, "pp0");
        FireFly176_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly176_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(511, "ip-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(11, "pimd");
        FireFly176_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly176_IF_IFNAME_MAP.put(9, "ipip");
        FireFly176_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly176_IF_IFNAME_MAP.put(4, "lsi");
        FireFly176_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly176_IF_IFNAME_MAP.put(512, "lsq-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(512, "lsq-0/0/0");
        FireFly176_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly176_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly176_IF_IFNAME_MAP.put(502, "st0");
        FireFly176_IF_IFDESCR_MAP.put(502, "st0");
        FireFly176_IF_IFNAME_MAP.put(8, "gre");
        FireFly176_IF_IFDESCR_MAP.put(8, "gre");
        FireFly176_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly176_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly176_IF_IFNAME_MAP.put(12, "mtun");
        FireFly176_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly176_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly176_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.177"), 16);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("10.0.4.177"), 517);
        FireFly177_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("10.0.4.177"), InetAddressUtils.addr("255.255.255.0"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.177"), InetAddressUtils.addr("255.255.255.255"));
        FireFly177_IF_IFNAME_MAP.put(8, "gre");
        FireFly177_IF_IFDESCR_MAP.put(8, "gre");
        FireFly177_IF_IFNAME_MAP.put(5, "dsc");
        FireFly177_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly177_IF_IFNAME_MAP.put(11, "pimd");
        FireFly177_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly177_IF_IFNAME_MAP.put(4, "lsi");
        FireFly177_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly177_IF_IFNAME_MAP.put(7, "tap");
        FireFly177_IF_IFDESCR_MAP.put(7, "tap");
        FireFly177_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly177_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly177_IF_IFNAME_MAP.put(12, "mtun");
        FireFly177_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly177_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(514, "ge-0/0/0.0");
        FireFly177_IF_IFDESCR_MAP.put(514, "ge-0/0/0.0");
        FireFly177_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly177_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly177_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly177_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly177_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly177_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly177_IF_IFNAME_MAP.put(502, "st0");
        FireFly177_IF_IFDESCR_MAP.put(502, "st0");
        FireFly177_IF_IFNAME_MAP.put(508, "sp-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(508, "sp-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(501, "pp0");
        FireFly177_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly177_IF_IFNAME_MAP.put(10, "pime");
        FireFly177_IF_IFDESCR_MAP.put(10, "pime");
        FireFly177_IF_IFNAME_MAP.put(512, "mt-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(512, "mt-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(9, "ipip");
        FireFly177_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly177_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly177_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly177_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly177_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly177_IF_IFNAME_MAP.put(509, "gr-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(509, "gr-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(510, "ip-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(510, "ip-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(513, "lt-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(513, "lt-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly177_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly177_IF_IFNAME_MAP.put(507, "ge-0/0/1");
        FireFly177_IF_IFDESCR_MAP.put(507, "ge-0/0/1");
        FireFly177_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly177_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly177_IF_IFNAME_MAP.put(511, "lsq-0/0/0");
        FireFly177_IF_IFDESCR_MAP.put(511, "lsq-0/0/0");
        FireFly177_IF_IFNAME_MAP.put(6, "lo0");
        FireFly177_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly177_IF_IFNAME_MAP.put(505, "vlan");
        FireFly177_IF_IFDESCR_MAP.put(505, "vlan");
        
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("192.168.168.189"), 16);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.6"), 516);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.16"), 22);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.1"), 22);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("10.205.56.189"), 507);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("128.0.0.4"), 22);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("128.0.1.16"), 22);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("20.0.0.189"), 517);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.6"), 516);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("10.0.0.1"), 22);
        FireFly189_IP_IF_MAP.put(InetAddressUtils.addr("127.0.0.1"), 21);

        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("10.0.0.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("10.205.56.189"), InetAddressUtils.addr("255.255.0.0"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("20.0.0.189"), InetAddressUtils.addr("255.255.255.0"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("127.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.1"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.4"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("128.0.0.6"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("128.0.1.16"), InetAddressUtils.addr("255.255.255.255"));
        FireFly189_IP_MK_MAP.put(InetAddressUtils.addr("192.168.168.189"), InetAddressUtils.addr("255.255.255.255"));

        FireFly189_IF_IFNAME_MAP.put(12, "mtun");
        FireFly189_IF_IFDESCR_MAP.put(12, "mtun");
        FireFly189_IF_IFNAME_MAP.put(517, "ge-0/0/1.0");
        FireFly189_IF_IFDESCR_MAP.put(517, "ge-0/0/1.0");
        FireFly189_IF_IFNAME_MAP.put(4, "lsi");
        FireFly189_IF_IFDESCR_MAP.put(4, "lsi");
        FireFly189_IF_IFNAME_MAP.put(8, "gre");
        FireFly189_IF_IFDESCR_MAP.put(8, "gre");
        FireFly189_IF_IFNAME_MAP.put(507, "ge-0/0/0.0");
        FireFly189_IF_IFDESCR_MAP.put(507, "ge-0/0/0.0");
        FireFly189_IF_IFNAME_MAP.put(501, "pp0");
        FireFly189_IF_IFDESCR_MAP.put(501, "pp0");
        FireFly189_IF_IFNAME_MAP.put(10, "pime");
        FireFly189_IF_IFDESCR_MAP.put(10, "pime");
        FireFly189_IF_IFNAME_MAP.put(515, "sp-0/0/0.0");
        FireFly189_IF_IFDESCR_MAP.put(515, "sp-0/0/0.0");
        FireFly189_IF_IFNAME_MAP.put(9, "ipip");
        FireFly189_IF_IFDESCR_MAP.put(9, "ipip");
        FireFly189_IF_IFNAME_MAP.put(510, "gr-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(510, "gr-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(22, "lo0.16385");
        FireFly189_IF_IFDESCR_MAP.put(22, "lo0.16385");
        FireFly189_IF_IFNAME_MAP.put(5, "dsc");
        FireFly189_IF_IFDESCR_MAP.put(5, "dsc");
        FireFly189_IF_IFNAME_MAP.put(506, "ge-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(506, "ge-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(248, "lo0.32768");
        FireFly189_IF_IFDESCR_MAP.put(248, "lo0.32768");
        FireFly189_IF_IFNAME_MAP.put(512, "lsq-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(512, "lsq-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(503, "ppd0");
        FireFly189_IF_IFDESCR_MAP.put(503, "ppd0");
        FireFly189_IF_IFNAME_MAP.put(513, "mt-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(513, "mt-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(16, "lo0.0");
        FireFly189_IF_IFDESCR_MAP.put(16, "lo0.0");
        FireFly189_IF_IFNAME_MAP.put(6, "lo0");
        FireFly189_IF_IFDESCR_MAP.put(6, "lo0");
        FireFly189_IF_IFNAME_MAP.put(502, "st0");
        FireFly189_IF_IFDESCR_MAP.put(502, "st0");
        FireFly189_IF_IFNAME_MAP.put(514, "lt-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(514, "lt-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(509, "sp-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(509, "sp-0/0/0");
        FireFly189_IF_IFNAME_MAP.put(516, "sp-0/0/0.16383");
        FireFly189_IF_IFDESCR_MAP.put(516, "sp-0/0/0.16383");
        FireFly189_IF_IFNAME_MAP.put(504, "ppe0");
        FireFly189_IF_IFDESCR_MAP.put(504, "ppe0");
        FireFly189_IF_IFNAME_MAP.put(7, "tap");
        FireFly189_IF_IFDESCR_MAP.put(7, "tap");
        FireFly189_IF_IFNAME_MAP.put(505, "vlan");
        FireFly189_IF_IFDESCR_MAP.put(505, "vlan");
        FireFly189_IF_IFNAME_MAP.put(11, "pimd");
        FireFly189_IF_IFDESCR_MAP.put(11, "pimd");
        FireFly189_IF_IFNAME_MAP.put(508, "ge-0/0/1");
        FireFly189_IF_IFDESCR_MAP.put(508, "ge-0/0/1");
        FireFly189_IF_IFNAME_MAP.put(511, "ip-0/0/0");
        FireFly189_IF_IFDESCR_MAP.put(511, "ip-0/0/0");    } catch (Exception ignored) {
        
    }
    }
    
    public OnmsNode getFireFly170() {
        return getNode(FireFly170_NAME,FireFly170_SYSOID,FireFly170_IP,FireFly170_IP_IF_MAP,FireFly170_IF_IFNAME_MAP,FireFly170_IF_MAC_MAP,FireFly170_IF_IFDESCR_MAP,FireFly170_IF_IFALIAS_MAP,FireFly170_IP_MK_MAP);
    }    

    public OnmsNode getFireFly171() {
        return getNode(FireFly171_NAME,FireFly171_SYSOID,FireFly171_IP,FireFly171_IP_IF_MAP,FireFly171_IF_IFNAME_MAP,FireFly171_IF_MAC_MAP,FireFly171_IF_IFDESCR_MAP,FireFly171_IF_IFALIAS_MAP,FireFly171_IP_MK_MAP);
    }    

    public OnmsNode getFireFly172() {
        return getNode(FireFly172_NAME,FireFly172_SYSOID,FireFly172_IP,FireFly172_IP_IF_MAP,FireFly172_IF_IFNAME_MAP,FireFly172_IF_MAC_MAP,FireFly172_IF_IFDESCR_MAP,FireFly172_IF_IFALIAS_MAP,FireFly172_IP_MK_MAP);
    }    

    public OnmsNode getFireFly173() {
        return getNode(FireFly173_NAME,FireFly173_SYSOID,FireFly173_IP,FireFly173_IP_IF_MAP,FireFly173_IF_IFNAME_MAP,FireFly173_IF_MAC_MAP,FireFly173_IF_IFDESCR_MAP,FireFly173_IF_IFALIAS_MAP,FireFly173_IP_MK_MAP);
    }    

    public OnmsNode getFireFly174() {
        return getNode(FireFly174_NAME,FireFly174_SYSOID,FireFly174_IP,FireFly174_IP_IF_MAP,FireFly174_IF_IFNAME_MAP,FireFly174_IF_MAC_MAP,FireFly174_IF_IFDESCR_MAP,FireFly174_IF_IFALIAS_MAP,FireFly174_IP_MK_MAP);
    }    

    public OnmsNode getFireFly175() {
        return getNode(FireFly175_NAME,FireFly175_SYSOID,FireFly175_IP,FireFly175_IP_IF_MAP,FireFly175_IF_IFNAME_MAP,FireFly175_IF_MAC_MAP,FireFly175_IF_IFDESCR_MAP,FireFly175_IF_IFALIAS_MAP,FireFly175_IP_MK_MAP);
    }    

    public OnmsNode getFireFly176() {
        return getNode(FireFly176_NAME,FireFly176_SYSOID,FireFly176_IP,FireFly176_IP_IF_MAP,FireFly176_IF_IFNAME_MAP,FireFly176_IF_MAC_MAP,FireFly176_IF_IFDESCR_MAP,FireFly176_IF_IFALIAS_MAP,FireFly176_IP_MK_MAP);
    }    

    public OnmsNode getFireFly177() {
        return getNode(FireFly177_NAME,FireFly177_SYSOID,FireFly177_IP,FireFly177_IP_IF_MAP,FireFly177_IF_IFNAME_MAP,FireFly177_IF_MAC_MAP,FireFly177_IF_IFDESCR_MAP,FireFly177_IF_IFALIAS_MAP,FireFly177_IP_MK_MAP);
    }    

    public OnmsNode getFireFly189() {
        return getNode(FireFly189_NAME,FireFly189_SYSOID,FireFly189_IP,FireFly189_IP_IF_MAP,FireFly189_IF_IFNAME_MAP,FireFly189_IF_MAC_MAP,FireFly189_IF_IFDESCR_MAP,FireFly189_IF_IFALIAS_MAP,FireFly189_IP_MK_MAP);
    }    

}
