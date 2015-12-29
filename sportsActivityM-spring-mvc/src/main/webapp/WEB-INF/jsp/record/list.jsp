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
            <th>name</th>
            <th>age</th>
            <th>email</th>
            <th>height</th>
            <th>weight</th>
            <th>sex</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td>${record.id}</td>
                <td><c:out value="${record.name}"/></td>
                <td><c:out value="${record.age}"/></td>
                <td><c:out value="${record.email}"/></td>
                <td><c:out value="${record.height}"/></td>
                <td><c:out value="${record.weight}"/></td>
                <td><c:out value="${record.sex}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>