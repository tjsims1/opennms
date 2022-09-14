/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2014 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.enlinkd.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Type;
import org.opennms.netmgt.model.FilterManager;
import org.opennms.netmgt.model.OnmsNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;

import static org.opennms.core.utils.InetAddressUtils.str;

@Entity
@Table(name = "ospfArea")
@Filter(name = FilterManager.AUTH_FILTER_NAME, condition = "exists (select distinct x.nodeid from node x join category_node cn on x.nodeid = cn.nodeid join category_group cg on cn.categoryId = cg.categoryId where x.nodeid = nodeid and cg.groupId in (:userGroups))")
public class OspfArea implements Serializable {

    private final static Logger LOG = LoggerFactory.getLogger(OspfArea.class);


    private static final long serialVersionUID = 3798160983917807494L;
    private Integer m_id;
    private OnmsNode m_node;



    private InetAddress m_ospfAreaId;
    private Integer m_ospfAuthType;
    private Integer m_ospfImportAsExtern;
    private Integer m_ospfAreaBdrRtrCount;
    private Integer m_ospfAsBdrRtrCount;

    public OspfArea() {}

    @Id
    @Column(nullable = false)
    @SequenceGenerator(name = "opennmsSequence", sequenceName = "opennmsNxtId")
    @GeneratedValue(generator = "opennmsSequence")
    public Integer getId() {
        return m_id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nodeId")
    public OnmsNode getNode() {
        return m_node;
    }

    @Type(type = "org.opennms.netmgt.model.InetAddressUserType")
    @Column(name = "ospfAreaId")
    public InetAddress getOspfAreaId() {
        return m_ospfAreaId;
    }

    @Column(name = "ospfAuthType")
    public Integer getOspfAuthType() {
        return m_ospfAuthType;
    }

    @Column(name = "ospfImportAsExtern")
    public Integer getOspfImportAsExtern() {
        return m_ospfImportAsExtern;
    }

    @Column(name = "ospfAreaBdrRtrCount")
    public Integer getOspfAreaBdrRtrCount() {
        return m_ospfAreaBdrRtrCount;
    }

    @Column(name = "ospfAsBdrRtrCount")
    public Integer getOspfAsBdrRtrCount() {
        return m_ospfAsBdrRtrCount;
    }

    public OspfArea setId(Integer id) {
        this.m_id = m_id;
        return this;
    }

    public OspfArea setNode(OnmsNode node) {
        this.m_node = m_node;
        return this;
    }

    public OspfArea setOspfAreaId(InetAddress ospfAreaId) {
        this.m_ospfAreaId = ospfAreaId;
        return this;
    }

    public OspfArea setOspfAuthType(Integer ospfAuthType) {
        this.m_ospfAuthType = ospfAuthType;
        return this;
    }

    public OspfArea setOspfImportAsExtern(Integer ospfImportAsExtern) {
        this.m_ospfImportAsExtern = ospfImportAsExtern;
        return this;
    }

    public OspfArea setOspfAreaBdrRtrCount(Integer ospfAreaBdrRtrCount) {
        this.m_ospfAreaBdrRtrCount = ospfAreaBdrRtrCount;
        return this;
    }

    public OspfArea setOspfAsBdrRtrCount(Integer ospfAsBdrRtrCount) {
        this.m_ospfAsBdrRtrCount = ospfAsBdrRtrCount;
        return this;
    }

    /**
     * <p>toString</p>
     *
     * @return a {@link String} object.
     */
    public String toString() {
        return "ospfArea: nodeid:[" +
                (getNode() != null ? getNode().getId() :null) +
                "]: area [" +
                str(getOspfAreaId()) +
                "/" +
                getOspfAuthType() +
                "/" +
                getOspfImportAsExtern() +
                "/" +
                getOspfAreaBdrRtrCount() +
                "]: rem router id/ip/addressless:[" +
                getOspfAsBdrRtrCount() +
                "]";
    }

}
