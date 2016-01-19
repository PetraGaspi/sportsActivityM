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
        <h3>Welcome back!</h3>

        <h3>Recent records overview (last ${days} days):</h3>
    </div>


    <table class="table">
        <thead>
        <tr>
            <th>date</th>
            <th>duration</th>
            <th>distance</th>
            <th>customer</th>
            <th>activity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td><b><fmt:formatDate value="${record.date.time}" pattern="yyyy-MM-dd"/></b></td>
                <td><c:out value="${record.duration}"/></td>
                <td><c:out value="${record.distance}"/></td>
                <td><a href='/pa165/user/<c:out value="${record.user.id}"/>'><c:out
                        value="${record.user.name}"/></a></td>
                <td><a href='/pa165/activity/<c:out value="${record.activity.id}"/>'><c:out
                        value="${record.activity.name}"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>