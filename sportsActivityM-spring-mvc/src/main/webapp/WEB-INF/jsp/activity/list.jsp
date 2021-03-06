<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Activities">
<jsp:attribute name="body">

    <table class="table">
        <caption>Bulk info - Activities</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>distance measured</th>
            <th>calories per hour</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${activities}" var="activity">
            <tr>
                <td>${activity.id}</td>
                <td><c:out value="${activity.name}"/></td>
                <td><c:out value="${activity.measureDistance}"/></td>
                <td><c:out value="${activity.calories.index}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>