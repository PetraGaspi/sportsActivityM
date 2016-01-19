<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Users">
<jsp:attribute name="body">

    <table class="table">
        <caption>Bulk info - Users</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>age</th>
            <th>email</th>
            <th>height</th>
            <th>weight</th>
            <th>sex</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.height}"/></td>
                <td><c:out value="${user.weight}"/></td>
                <td><c:out value="${user.sex}"/></td>
                <td><a href="delete/${user.id}">delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>