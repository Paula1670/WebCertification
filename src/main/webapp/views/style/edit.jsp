<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form id="styleForm" action="administrator/style/edit.do" modelAttribute="style">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="nombre">
		<spring:message code="style.name" />:
	</form:label>
	<form:input path="nombre" />
	<form:errors cssClass="error" path="nombre" />
	<br />

	<form:label path="descripcion">
		<spring:message code="style.description" />:
	</form:label>
	<form:input path="descripcion" />
	<form:errors cssClass="error" path="descripcion" />
	<br />
	
	
	
	<jstl:if test="${not empty mensaje}">
		<div class="error">
			<spring:message code="${mensaje}" />
		</div>
	</jstl:if>
	<input type="submit" name="save"
		value="<spring:message code="style.save" />" />&nbsp; 
	<jstl:if test="${style.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="style.delete" />"
			onclick="return confirm('<spring:message code="style.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="style.cancel" />"
		onclick="javascript: relativeRedir('administrator/style/list.do');" />
	<br />

</form:form>
