<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New activity">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/activity/create"
               modelAttribute="activityCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">

            <div class="col-sm-5">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <form:input path="name" cssClass="form-control"/>

                <form:label path="measureDistance" cssClass="col-sm-2 control-label">Measurable</form:label>
                <form:select path="measureDistance" cssClass="form-control">
                    <form:option value="true">true</form:option>
                    <form:option value="false">false</form:option>
                </form:select>

                <form:label path="calories" cssClass="col-sm-2 control-label">Calories/hour</form:label>
                <form:input path="calories" cssClass="form-control"/>

                <form:errors path="calories" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create activity</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>