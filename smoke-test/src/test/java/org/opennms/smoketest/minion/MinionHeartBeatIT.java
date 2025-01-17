/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2016-2021 The OpenNMS Group, Inc.
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
package org.opennms.smoketest.minion;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.opennms.core.criteria.CriteriaBuilder;
import org.opennms.netmgt.dao.api.MinionDao;
import org.opennms.netmgt.dao.api.NodeDao;
import org.opennms.netmgt.dao.hibernate.MinionDaoHibernate;
import org.opennms.netmgt.dao.hibernate.NodeDaoHibernate;
import org.opennms.netmgt.model.OnmsNode;
import org.opennms.netmgt.model.minion.OnmsMinion;
import org.opennms.smoketest.junit.MinionTests;
import org.opennms.smoketest.stacks.OpenNMSStack;
import org.opennms.smoketest.utils.DaoUtils;

@Category(MinionTests.class)
public class MinionHeartBeatIT {

	@ClassRule
	public static final OpenNMSStack stack = OpenNMSStack.MINION;

    @Test
	public void minionHeartBeatTestForLastUpdated() {

		final String fs = "Minions";
		final String fid = stack.minion().getId();
		final String location = stack.minion().getLocation();

		final Date startOfTest = new Date();
		final MinionDao minionDao = stack.postgres().dao(MinionDaoHibernate.class);
		final NodeDao nodeDao = stack.postgres().dao(NodeDaoHibernate.class);

		// The heartbeat runs every minute so if we miss the first one, poll long enough
		// to catch the next one
		await().atMost(90, SECONDS)
			   .pollInterval(5, SECONDS)
			   .until(DaoUtils.countMatchingCallable(minionDao, new CriteriaBuilder(OnmsMinion.class)
															 .ge("lastUpdated", startOfTest)
															 .toCriteria()), greaterThan(0));

		await().atMost(180, SECONDS)
			   .pollInterval(5, SECONDS)
			   .until(DaoUtils.countMatchingCallable(nodeDao, new CriteriaBuilder(OnmsNode.class)
															 .eq("foreignSource", fs)
															 .eq("foreignId", fid)
															 .toCriteria()), equalTo(1));

		assertThat(nodeDao.get(fs + ":" + fid).getLocation().getLocationName(), equalTo(location));
	}
}
