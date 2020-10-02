<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>

<html>
<head>
    <c:if test="${empty user.name}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty user.name}">
        <title>Edit</title>
    </c:if>
</head>
<body>
<c:if test="${empty user.name}">
    <c:url value="/admin/add" var="var"/>
</c:if>
<c:if test="${!empty user.name}">
    <c:url value="/admin/edit" var="var"/>
</c:if>

<!--<form action="${var}" method="POST">-->
<form action="${var}" method="POST" modelAttribute="user">
    <c:if test="${!empty user.name}">
        <input type="hidden" name="id" value="${user.id}">
    </c:if>

    <label for="name">Name</label>
    <input type="text" name="name" id="name">

    <label for="password">Password</label>
    <input type="text" name="password" id="password">

    <!--<label for="role">Role</label>-->
    <!--<td><form:label path = "getRole">Role</form:label></td>-->
    <!--<td><form:input path = "getRole" /></td>-->
    <!--<input type="text" name="role" id="role">-->

    <!--
    <select name="role" size="1" id="role">
        <c:forEach items="${getRole.listIterator()}" var="roles">
            <option value="${roles}">${roles}</option>
        </c:forEach>
    </select>
    -->
    <!-- рабочий (roles) -->
    <form:select path="getRole">
        <form:option value="NONE" label="--- Select ---" />
        <form:options items="${getRole}" itemValue="role" itemLabel="role" />
    </form:select>

    <c:if test="${empty user.name}">
        <input type="submit" value="Add new user">
    </c:if>
    <c:if test="${!empty user.name}">
        <input type="submit" value="Edit user">
    </c:if>

</form>
</body>
</html>
