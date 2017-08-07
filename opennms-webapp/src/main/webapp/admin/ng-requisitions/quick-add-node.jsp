<%--
/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2015 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2015 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/includes/bootstrap.jsp" flush="false">
    <jsp:param name="norequirejs" value="true" />
    <jsp:param name="nobreadcrumbs" value="true" />
    <jsp:param name="ngapp" value="onms-requisitions" />
    <jsp:param name="title" value="Quick-Add Node" />
    <jsp:param name="headTitle" value="Quick-Add Node" />
    <jsp:param name="headTitle" value="Admin" />
    <jsp:param name="location" value="admin" />

    <jsp:param name="link" value='<link rel="stylesheet" type="text/css" href="webjars/angular-loading-bar/0.9.0/build/loading-bar.css" />' />
    <jsp:param name="link" value='<link rel="stylesheet" type="text/css" href="webjars/angular-growl-v2/build/angular-growl.css" />' />

    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular/1.5.8/angular.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-resource/1.5.8/angular-resource.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-cookies/1.5.8/angular-cookies.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-sanitize/1.5.8/angular-sanitize.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-route/1.5.8/angular-route.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-animate/1.5.8/angular-animate.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-bootstrap/2.1.3/ui-bootstrap-tpls.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-loading-bar/0.9.0/build/loading-bar.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/angular-growl-v2/build/angular-growl.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/ip-address/5.8.2/dist/ip-address-globals.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="webjars/bootbox/4.4.0/bootbox.js"></script>' />

    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/app-quickaddnode.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/model/RequisitionInterface.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/model/RequisitionNode.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/model/Requisition.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/model/RequisitionsData.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/model/QuickNode.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/services/Requisitions.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/directives/requisitionConstraints.js"></script>' />
    <jsp:param name="script" value='<script type="text/javascript" src="js/onms-requisitions/scripts/controllers/QuickAddNode.js"></script>' />
</jsp:include>

<div ng-view></div>
<div growl></div>

<jsp:include page="/includes/bootstrap-footer.jsp" flush="false"/>
