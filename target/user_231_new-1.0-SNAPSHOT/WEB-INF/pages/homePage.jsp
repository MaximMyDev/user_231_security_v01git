<%--
  Created by IntelliJ IDEA.
  User: Root
  Date: 23.09.2020
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h1>Hello, welcome to the CRUD portal (v0.242.07)</h1>
</br>
</br>
<h3>Please sign in...<c:url value="/login" var="login"/> <a href="${login}">here</a></h3>
</br>
</body>
</html>
