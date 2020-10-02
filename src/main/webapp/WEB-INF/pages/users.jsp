<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>list Users</title>
</head>
<br>
<h2>Users</h2>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <!--<th>sex</th>-->
    </tr>
    <c:forEach var="userCurrent" items="${userList}">
        <tr>
            <td>${userCurrent.id}</td>
            <td>${userCurrent.name}</td>
            <td>${userCurrent.password}</td>
            <td>
                <a href="/edit/${userCurrent.id}">edit</a>
                <a href="/delete/${userCurrent.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h3>Add</h3>
<c:url value="/add" var="add"/>
<a href="${add}">Add new user</a>
</br>
<h3>Login...</h3>
<c:url value="/login" var="login"/>
<a href="${login}">login now</a>

</body>
</html>