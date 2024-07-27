<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="academies" requestURI="academy/list.do" id="row">
	
	<!-- Attributes -->

	<spring:message code="academy.comercialname" var="nombreComercialHeader" />
	<display:column property="nombreComercial" title="${nombreComercialHeader}" sortable="true" />

	<display:column>
			<a href="course/listByAcademiaId.do?academyId=${row.id}">
				<spring:message	code="academy.see.course" />
			</a>
	</display:column>
		<display:column>
			<a href="tutorials/listByAcademiaId.do?academyId=${row.id}">
				<spring:message	code="academy.see.tutorial" />
			</a>
	</display:column>
</display:table>