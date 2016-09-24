<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/2/16
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Asset</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="/editor/asset" method="post">
        <div class="form-group"><h1>New Asset</h1></div>
        <h3>Choice author</h3>
        <select class="selectpicker form-control form-group" name="author">
            <option value="-1">Default</option>
            <c:forEach items="${authors}" var="author">
                <option value="${author.id}">${author.name}</option>
            </c:forEach>
        </select>
        <input class="form-control form-group" type="text" name="title" placeholder="Title">
        <input class="form-control form-group" type="text" name="description" placeholder="Short description">
        <input class="form-control form-group" type="text" name="price" placeholder="Price">
        <input class="form-control form-group" type="text" name="tag" placeholder="Tags">
        <input type="file" name="image" multiple accept="image/jpeg,image/png" value="Add main picture">
        <input type="file" name="pictures" multiple accept="image/jpeg,image/png" value="Add pictures">
        <input type="submit" class="btn btn-primary" value="Add">
    </form>
</div>

<script>
    $('.selectpicker').selectpicker();
</script>
</body>
</html>
