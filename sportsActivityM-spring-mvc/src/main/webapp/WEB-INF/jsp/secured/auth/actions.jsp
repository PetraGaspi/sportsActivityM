<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <form:errors path="error" cssClass="help-block"/>
        <h3>Entity administration:</h3>

        <h4><a href="activity/list">Activities ></a></h4>
        <h4><a href="record/list">Records ></a></h4>
        <h4><a href="user/list">Users ></a></h4>
    </div>

</jsp:attribute>
</my:pagetemplate>
