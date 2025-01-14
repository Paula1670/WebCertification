<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->
<jstl:choose>
	
	<jstl:when test="${not empty tutorials}">
		<table>
			<thead>
				<tr>
					<th><spring:message code="tutorial.tutorial" /></th>
					<th><spring:message code="tutorial.descripcion" /></th>
					<th><spring:message code="tutorial.visualizaciones" /></th>
					<th><spring:message code="tutorial.video" /></th>
					<th><spring:message code="tutorial.remove" /></th>
					
				</tr>
			</thead>
			<tbody>
				<jstl:forEach items="${tutorials}" var="tutorial">
 					<tr>
						<td>${tutorial.tutorial}</td>
						<td>${tutorial.descripcion}</td>
						<td>${tutorial.visualizaciones}</td>
						<td>${tutorial.video}</td>
						<td>
						<button>
						<spring:message code="tutorial.remove" />
						 ${tutorial.id}</button>
						</td>
					</tr> 
				</jstl:forEach>
			</tbody>
		</table>
	</jstl:when>
	<jstl:otherwise>
		<spring:message code="tutorial.notfound" />
	</jstl:otherwise>
</jstl:choose>
