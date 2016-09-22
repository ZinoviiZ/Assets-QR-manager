<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/9/16
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/5/16
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sand Box</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h3><a href="/admin/users">Users list</a></h3>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li><button type="button" id="add_user" class="btn btn-default navbar-btn">Add User</button></li>
                    <li><button type="button" id="delete_user" class="btn btn-default navbar-btn">Delete selection</button></li>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Groups <span class="caret"></span></a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="/admin/users">Default</a></li>--%>
                            <%--<c:forEach items="${users}" var="user">--%>
                                <%--<li><a href="/group/${user.id}">${user.login}</a></li>--%>
                            <%--</c:forEach>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
            </div>
        </div>
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Login</b></td>
            <td><b>Email</b></td>
            <td><b>Role</b></td>
            <td><b>Date of registration</b></td>
            <td><b>Date of last seen</b></td>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><input type="checkbox" name="toDelete[]" value="${user.id}" id="checkbox_${user.id}"/></td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.registret_time}</td>
                <td>${user.last_seen_time}</td>
            </tr>
        </c:forEach>
    </table>
    <c:url value="/" var="backToList" />
    <p>Click to logout: <a href="${backToList}">BACK</a></p>
</div>

<script>
    $('.dropdown-toggle').dropdown();

    $('#add_user').click(function(){
        window.location.href='/admin/add_user_page';
    });

    $('#delete_user').click(function(){
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/admin/delete", data, function(data, status) {
            window.location.reload();
        });
    });


</script>
</body>
</html>
