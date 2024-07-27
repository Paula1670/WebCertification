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

<form:form id="actorForm" action="actor/create.do" modelAttribute="userAccount" method="POST">

	<form:hidden path="id" />
	<form:hidden path="version" />

    <form:label path="username">
		<spring:message code="useraccount.username" />:
	</form:label>
	<form:input path="username" />
	<form:errors cssClass="error" path="username" />
	<br />
    
    <form:label path="password">
		<spring:message code="useraccount.password" />:
	</form:label>
	<form:input type="password" path="password" />
	<form:errors cssClass="error" path="password" />
	<br />

    <form:label path="authorities">
        <spring:message code="useraccount.type" />:
    </form:label>
    <form:select id="authorities" path="authorities" multiple="false" >
        <jstl:forEach items="${authoritiesElement}" var ="authority">
        	<form:option value="${authority}" itemvalue="${authority}">${authority}</form:option>
        </jstl:forEach>
    </form:select>
    <form:errors path="authorities" cssClass="error"/>

	<jstl:if test="${not empty mensaje}">
		<div class="error">
			<spring:message code="${mensaje}"/>
		</div>
	</jstl:if>
	<input type="submit" name="next"
		value="<spring:message code="actor.next" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: relativeRedir('');" />
	<br />
	
	
</form:form>