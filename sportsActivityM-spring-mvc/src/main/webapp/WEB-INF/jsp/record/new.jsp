<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New record">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/record/create"
               modelAttribute="recordCreate" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">

            <div class="col-sm-5">
                <form:label path="date" cssClass="col-sm-2 control-label">Date</form:label>
                <form:input path="date" cssClass="form-control"/>

                <form:label path="duration" cssClass="col-sm-2 control-label">Measurable</form:label>   
                <form:input path="duration" cssClass="form-control"/>

                <form:label path="distance" cssClass="col-sm-2 control-label">Calories/hour</form:label>
                <form:input path="distance" cssClass="form-control"/>

                <form:select path="userId" cssClass="form-control">
                    <c:forEach items="${users}" var="customer">
                        <form:option value="<c:out value=${customer.id}/>"><c:out value="${customer.name}"/></form:option>
                    </c:forEach>
                </form:select>

                <form:select path="activityId" cssClass="form-control">
                    <c:forEach items="${activities}" var="activity">
                        <form:option value="<c:out value=${activity.id}/>"><c:out value="${activity.name}"/></form:option>
                    </c:forEach>
                </form:select>

                <form:errors path="calories" cssClass="help-block"/>
            </div>
        </div>
        <button class="btn btn-primary" type="submit">Create activity</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>