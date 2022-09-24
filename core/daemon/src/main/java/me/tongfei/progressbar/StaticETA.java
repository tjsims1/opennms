/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2007-2022 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2022 The OpenNMS Group, Inc.
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

package me.tongfei.progressbar;

import java.time.Duration;
import java.util.Optional;

/**
 * Class to get around a visibility issue in the progress bar library.
 *
 * This will be removed once <a href="https://github.com/ctongfei/progressbar/pull/146">PR #146</a> is released.
 */
public class StaticETA {
    public static void setETAOnBuilder(ProgressBarBuilder builder, Duration eta) {
        var estimatedCompletion = System.currentTimeMillis() + eta.toMillis();
        builder.setETAFunction((a, b) -> Optional.of(Duration.ofMillis(Math.max(estimatedCompletion - System.currentTimeMillis(), 0))));
    }
}