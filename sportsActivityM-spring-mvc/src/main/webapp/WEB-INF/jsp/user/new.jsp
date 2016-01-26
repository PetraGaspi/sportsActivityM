<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="User registration">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/user/create"
               modelAttribute="userCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <div class="col-sm-5">
                <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${email_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="email" cssClass="col-sm-2 control-label">Email</form:label>
                <form:input path="email" cssClass="form-control"/>
                <form:errors path="email" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${password_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="password" cssClass="col-sm-2 control-label">Password</form:label>
                <form:password path="password" cssClass="form-control"/>
                <form:errors path="password" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${age_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="age" cssClass="col-sm-2 control-label">Age</form:label>
                <form:input path="age" cssClass="form-control"/>
                <form:errors path="age" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${weight_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="weight" cssClass="col-sm-2 control-label">Weight</form:label>
                <form:input path="weight" cssClass="form-control"/>
                <form:errors path="weight" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${height_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="height" cssClass="col-sm-2 control-label">Height</form:label>
                <form:input path="height" cssClass="form-control"/>
                <form:errors path="height" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${SEX_error?'has-error':''}">
            <div class="col-sm-5">

                <form:label path="SEX" cssClass="col-sm-2 control-label">Sex</form:label>
                <form:select path="SEX" cssClass="form-control">
                    <form:option value="Male">Male</form:option>
                    <form:option value="Female">Female</form:option>
                </form:select>
                <form:errors path="SEX" cssClass="help-block"/>
            </div>
        </div>


        <button class="btn btn-primary" type="submit">Create user</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>