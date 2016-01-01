<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Records">
<jsp:attribute name="body">

    <table class="table">
        <caption>Bulk info - Records</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>date</th>
            <th>duration</th>
            <th>distance</th>
            <th>customer name</th>
            <th>activity name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td>${record.id}</td>
                <td><fmt:formatDate value="${record.date.time}" pattern="yyyy-MM-dd"/></td>
                <td><c:out value="${record.duration}"/></td>
                <td><c:out value="${record.distance}"/></td>
                <td><a href='/pa165/mvc/user/<c:out value="${record.user.id}"/>'><c:out value="${record.user.name}"/></a></td>
                <td><a href='/pa165/mvc/activity/<c:out value="${record.activity.id}"/>'><c:out value="${record.activity.name}"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>