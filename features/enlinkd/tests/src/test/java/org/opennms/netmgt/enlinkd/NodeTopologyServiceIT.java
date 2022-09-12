/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2018-2018 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2018 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.enlinkd;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.opennms.netmgt.enlinkd.model.IpInterfaceTopologyEntity;
import org.opennms.netmgt.enlinkd.model.NodeTopologyEntity;
import org.opennms.netmgt.enlinkd.service.api.NodeTopologyService;
import org.opennms.netmgt.enlinkd.service.api.SubNetwork;
import org.opennms.netmgt.nb.Nms0001NetworkBuilder;
import org.opennms.netmgt.nb.Nms0002NetworkBuilder;
import org.opennms.netmgt.nb.Nms003NetworkBuilder;
import org.opennms.netmgt.nb.Nms007NetworkBuilder;
import org.opennms.netmgt.nb.Nms0123NetworkBuilder;
import org.opennms.netmgt.nb.Nms101NetworkBuilder;
import org.opennms.netmgt.nb.Nms102NetworkBuilder;
import org.opennms.netmgt.nb.Nms1055NetworkBuilder;
import org.opennms.netmgt.nb.Nms17216NetworkBuilder;
import org.opennms.netmgt.nb.Nms4005NetworkBuilder;
import org.opennms.netmgt.nb.Nms4930NetworkBuilder;
import org.opennms.netmgt.nb.Nms6802NetworkBuilder;
import org.opennms.netmgt.nb.Nms7467NetworkBuilder;
import org.opennms.netmgt.nb.Nms7563NetworkBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class NodeTopologyServiceIT extends EnLinkdBuilderITCase {

    @Autowired
    private NodeTopologyService nodeTopologyService;

    @Test
    public void nms0001SubnetworksTest() {
        final Nms0001NetworkBuilder builder = new Nms0001NetworkBuilder();
        m_nodeDao.save(builder.getFroh());
        m_nodeDao.save(builder.getOedipus());
        m_nodeDao.save(builder.getSiegFrie());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(3));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
        assertThat(ips, hasSize(47));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        assertThat(subnets, hasSize(21));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        assertThat(legalsubnets, hasSize(1));
        SubNetwork legalsubnet = legalsubnets.iterator().next();
        assertThat(legalsubnet.getNodeIds(),hasSize(3));
    }

    @Test
    public void nms0002SubnetworksTest() {
        final Nms0002NetworkBuilder builder = new Nms0002NetworkBuilder();
        m_nodeDao.save(builder.getRluck001());
        m_nodeDao.save(builder.getSluck001());
        m_nodeDao.save(builder.getRPict001());
        m_nodeDao.save(builder.getRNewt103());
        m_nodeDao.save(builder.getRDeEssnBrue());
        m_nodeDao.save(builder.getSDeEssnBrue081());
        m_nodeDao.save(builder.getSDeEssnBrue121());
        m_nodeDao.save(builder.getSDeEssnBrue142());
        m_nodeDao.save(builder.getSDeEssnBrue165());
        m_nodeDao.save(builder.getRSeMalmNobe013());
        m_nodeDao.save(builder.getSSeMalmNobe561());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(11));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        assertThat(ips, hasSize(146));
        ips.forEach(System.err::println);

        final Set<SubNetwork> subnets = nodeTopologyService.findAllLegalSubNetwork();
        subnets.forEach(System.err::println);
        int n = 0;
        assertThat(subnets, hasSize(4));
        for (SubNetwork subnet: subnets) {
            n = n + subnet.getNodeIds().size();
        }
        assertEquals(11,n);
    }

    @Test
    public void nms003SubnetworkTests() {
        final Nms003NetworkBuilder builder = new Nms003NetworkBuilder();
        m_nodeDao.save(builder.getSwitch1());
        m_nodeDao.save(builder.getSwitch2());
        m_nodeDao.save(builder.getSwitch3());
        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(3));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
//        assertThat(ips, hasSize(8));
        ips.forEach(System.err::println);

        final Set<SubNetwork> subnets = nodeTopologyService.findAllLegalSubNetwork();
        subnets.forEach(System.err::println);
//        assertThat(subnets, hasSize(1));

    }
    @Test
    public void nms007SubnetworkTest() {
        final Nms007NetworkBuilder builder = new Nms007NetworkBuilder();
        m_nodeDao.save(builder.getFireFly170());
        m_nodeDao.save(builder.getFireFly171());
        m_nodeDao.save(builder.getFireFly172());
        m_nodeDao.save(builder.getFireFly173());
        m_nodeDao.save(builder.getFireFly174());
        m_nodeDao.save(builder.getFireFly175());
        m_nodeDao.save(builder.getFireFly176());
        m_nodeDao.save(builder.getFireFly177());
        m_nodeDao.save(builder.getFireFly189());
        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(9));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
//        assertThat(ips, hasSize(8));
        ips.forEach(System.err::println);

        final Set<SubNetwork> subnets = nodeTopologyService.findAllLegalSubNetwork();
        subnets.forEach(System.err::println);
//        assertThat(subnets, hasSize(1));

    }

    @Test
    public void nms101SubnetworksTest() {
        final Nms101NetworkBuilder builder = new Nms101NetworkBuilder();
        m_nodeDao.save(builder.getCisco1700());
        m_nodeDao.save(builder.getCisco1700b());
        m_nodeDao.save(builder.getCisco2691());
        m_nodeDao.save(builder.getCisco3600());
        m_nodeDao.save(builder.getCisco3700());
        m_nodeDao.save(builder.getCisco7200a());
        m_nodeDao.save(builder.getCisco7200b());
        m_nodeDao.save(builder.getLaptop());
        m_nodeDao.save(builder.getExampleCom());
        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(9));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
//        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
 //       assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
//        assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms102SubnetworksTest() {
        final Nms102NetworkBuilder builder = new Nms102NetworkBuilder();
        m_nodeDao.save(builder.getMikrotik());
        m_nodeDao.save(builder.getSamsung());
        m_nodeDao.save(builder.getMac1());
        m_nodeDao.save(builder.getMac2());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(4));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
//        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        //       assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
//        assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms0123SubnetworksTest() {
        final Nms0123NetworkBuilder builder = new Nms0123NetworkBuilder();
        m_nodeDao.save(builder.getItpn0111());
        m_nodeDao.save(builder.getItpn0112());
        m_nodeDao.save(builder.getItpn0113());
        m_nodeDao.save(builder.getItpn0114());
        m_nodeDao.save(builder.getItpn0121());
        m_nodeDao.save(builder.getItpn0123());
        m_nodeDao.save(builder.getItpn0201());
        m_nodeDao.save(builder.getItpn0202());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(8));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        assertThat(ips, hasSize(8));
        ips.forEach(System.err::println);

        final Set<SubNetwork> subnets = nodeTopologyService.findAllLegalSubNetwork();
        subnets.forEach(System.err::println);
        assertThat(subnets, hasSize(1));
        assertEquals(8,subnets.iterator().next().getNodeIds().size());
    }

    public void nms1055SubnetworksTest() {
        final Nms1055NetworkBuilder builder =new Nms1055NetworkBuilder();
        m_nodeDao.save(builder.getAustin());
        m_nodeDao.save(builder.getDelaware());
        m_nodeDao.save(builder.getPenrose());
        m_nodeDao.save(builder.getPhoenix());
        m_nodeDao.save(builder.getSanjose());
        m_nodeDao.save(builder.getRiovista());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(6));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
//        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
 //       assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
   //     assertThat(legalsubnets, hasSize(1));

    }

    public void nms4005SubnetworksTest() {
        final Nms4005NetworkBuilder builder = new Nms4005NetworkBuilder();
        m_nodeDao.save(builder.getR1());
        m_nodeDao.save(builder.getR2());
        m_nodeDao.save(builder.getR3());
        m_nodeDao.save(builder.getR4());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(4));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
//        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        //       assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        //     assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms4930SubnetworksTest() {
        final Nms4930NetworkBuilder builder = new Nms4930NetworkBuilder();
        m_nodeDao.save(builder.getDlink1());
        m_nodeDao.save(builder.getDlink2());
        m_nodeDao.save(builder.getHost1());
        m_nodeDao.save(builder.getHost2());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(4));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms6802SubnetworksTest() {
        final Nms6802NetworkBuilder builder = new Nms6802NetworkBuilder();
        m_nodeDao.save(builder.getCiscoIosXrRouter());
        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
        assertThat(ips, hasSize(1));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        assertThat(subnets, hasSize(1));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        assertThat(legalsubnets, hasSize(0));

    }

    public void nms7467SubnetworksTest() {
        final Nms7467NetworkBuilder builder = new Nms7467NetworkBuilder();
        m_nodeDao.save(builder.getCiscoC870());
        m_nodeDao.save(builder.getDarwin108());
        m_nodeDao.save(builder.getLinuxUbuntu());
        m_nodeDao.save(builder.getCiscoWsC2948());
        m_nodeDao.save(builder.getNetGearSw108());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(5));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
//        assertThat(ips, hasSize(7));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        //       assertThat(subnets, hasSize(4));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        //     assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms7563SubnetworksTest() {
        final Nms7563NetworkBuilder builder = new Nms7563NetworkBuilder();
        m_nodeDao.save(builder.getCisco01());
        m_nodeDao.save(builder.getHomeServer());
        m_nodeDao.save(builder.getSwitch02());

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);
        assertThat(ips, hasSize(5));

        final Set<SubNetwork> subnets = nodeTopologyService.findAllSubNetwork();
        subnets.forEach(System.err::println);
        assertThat(subnets, hasSize(3));

        final Set<SubNetwork> legalsubnets = nodeTopologyService.findAllLegalSubNetwork();
        legalsubnets.forEach(System.err::println);
        assertThat(legalsubnets, hasSize(1));

    }

    @Test
    public void nms17216SubnetworksTest() {
        final Nms17216NetworkBuilder builder = new Nms17216NetworkBuilder();
        m_nodeDao.save(builder.getSwitch1());
        m_nodeDao.save(builder.getSwitch2());

        final List<NodeTopologyEntity> nodes = nodeTopologyService.findAllNode();
        nodes.forEach(System.err::println);
        assertThat(nodes, hasSize(2));

        final List<IpInterfaceTopologyEntity> ips = nodeTopologyService.findAllIp();
        ips.forEach(System.err::println);

        assertThat(ips, hasSize(6));
    }


}
