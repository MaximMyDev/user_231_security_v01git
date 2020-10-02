<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin Page</title>
</head>
<br>
<security:authorize access="hasRole('ROLE_USER')">
    This text is only visible to a user
    <br/>
</security:authorize>
<security:authorize access="hasRole('ROLE_ADMIN')">
    This text is only visible to an admin
    <br/>
</security:authorize>
<h2>List Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>role</th>
    </tr>
    <c:forEach var="userCurrent" items="${userList}">
        <tr>
            <td>${userCurrent.id}</td>
            <td>${userCurrent.name}</td>
            <td>${userCurrent.password}</td>
            <td>${userCurrent.roles}</td>
            <td>
                <a href="/admin/edit/${userCurrent.id}">edit</a>
                <a href="/admin/delete/${userCurrent.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Add New User</h3>
<c:url value="/admin/add" var="add"/>
<a href="${add}">Add new user</a>
</body>
</html>