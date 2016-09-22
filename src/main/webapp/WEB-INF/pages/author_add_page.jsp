<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/2/16
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Author</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/editor/author" method="post">
        <div class="form-group"><h1>New Author</h1></div>
        <h3>Choice assets</h3>
        <select multiple class="selectpicker form-control form-group" name="asset">
            <option value="-1">Default</option>
            <c:forEach items="${assets}" var="asset">
                <option value="${asset.id}">${asset.title}</option>
            </c:forEach>
        </select>
        <div class="form-group"><input type="text" class="form-control" name="name" placeholder="Name"></div>
        <div class="form-group"><input type="text" class="form-control" name="phone" placeholder="Phone"></div>
        <div class="form-group"><input type="text" class="form-control" name="email" placeholder="Email"></div>
        <div class="form-group"><input type="text" class="form-control" name="website" placeholder="Website"></div>
        <div class="form-group"><input type="file" name="image" ></div>
        <div class="form-group"><input type="submit" class="btn btn-primary" value="Add"></div>
    </form>
</div>
<%--<div class="container">--%>
    <%--<form enctype="multipart/form-data" class="form-horizontal" action="/author/1/photo" method="post">--%>
        <%--<div class="form-group"><input type="submit" class="btn btn-primary" value="Add Photo"></div>--%>
    <%--</form>--%>
<%--</div>--%>
<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>