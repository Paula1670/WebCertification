<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<table>
	<thead>
		<tr>
			<th>
				<h3>
					<spring:message code="administrator.statistics.header.1"/>
				</h3>
				
			</th>
			<th>
				<h3>
					<spring:message code="administrator.statistics.header.2"/>
				</h3>
				
			</th>
		</tr>
		
	</thead>
	<tbody>
		<jstl:forEach items="${statistics}" var="entry">
			<tr>
				
				<th>
					<h4>
						<spring:message code="administrator.statistics.${entry.key}.title"/>
					</h4>
				</th>
			</tr>
			<jstl:forEach items="${entry.value}" var="value_entry">
				<tr>
					<td><spring:message code="administrator.statistics.${value_entry.key}"/></td>
					<td>${value_entry.value}</td>
				</tr>
			</jstl:forEach>
		</jstl:forEach>
	</tbody>
</table>