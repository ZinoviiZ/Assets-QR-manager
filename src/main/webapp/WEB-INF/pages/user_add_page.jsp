<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/9/16
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Contact</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/admin/add" method="post">
        <h3>New user</h3>
        <select class="selectpicker form-control form-group" name="user_role">
            <c:forEach items="${roles}" var="role">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="text" name="login" placeholder="Login">
        <input class="form-control form-group" type="password" name="password" placeholder="Password">
        <input class="form-control form-group" type="email" name="email" placeholder="Email">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
</div>

<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>