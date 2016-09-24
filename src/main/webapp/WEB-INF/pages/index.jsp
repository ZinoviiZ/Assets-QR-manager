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
    <c:set var="role" scope="session" value="GUEST"/>
    <c:set var="admin" scope="session" value="[ROLE_ADMIN]"/>


    <h3><a href="/">${info}</a></h3>
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <c:choose>
                <c:when test="${userRoles eq role}">
                    <h3><a href="/">Your`re ${role}</a></h3>
                    <button type="button" id="authorization_user" class="btn btn-default navbar-btn">Authorization</button>
                </c:when>
                <c:otherwise>
                    <h3>Glad to see you ${userName}. You have role:${userRoles} here</h3>
                    <ol>
                        <li style = "display: inline-block"><button type="button" id="add_asset" class="btn btn-default navbar-btn">Add Asset</button></li>
                        <li style = "display: inline-block"><button type="button" id="add_author" class="btn btn-default navbar-btn">Add Author</button></li>

                    <c:if test="${userRoles eq admin}">
                        <li style = "display: inline-block"><button type="button" id="users_page" class="btn btn-default navbar-btn">Users list</button></li>
                    </c:if>
                    </ol>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
    <h2>Asset`s table</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>Title</b></td>
            <td><b>Description</b></td>
            <td><b>Price</b></td>
            <td><b>Author</b></td>
            <td><b>Pictures</b></td>
        </tr>
        </thead>
        <c:forEach items="${assets}" var="asset">
            <tr>
                <td><img height="50" width="50" src="${asset.mainPicture.surl}"></td>
                <td>${asset.title}</td>
                <td>${asset.description}</td>
                <td>${asset.price}</td>
                <c:choose>
                    <c:when test="${asset.author ne null}">
                        <td>${asset.author.name}</td>
                    </c:when>
                    <c:otherwise>
                        <td>Sam po sebe:)</td>
                    </c:otherwise>
                </c:choose>
                <td><c:forEach items="${asset.pictures}" var="picture">
                    <img height="50" width="50" src="${picture.surl}">
                </c:forEach> </td>
            </tr>
        </c:forEach>
    </table>
    <h2>Author`s table</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>Photo</b></td>
            <td><b>QR</b></td>
            <td><b>Name</b></td>
            <td><b>Phone</b></td>
            <td><b>Email</b></td>
            <td><b>Website</b></td>
        </tr>
        </thead>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td><img height="50" width="50" src="${author.photo.surl}"></td>
                <td><img height="50" width="50" src="/author/qr/${author.id}"></td>
                <td>${author.name}</td>
                <td>${author.phone}</td>
                <td>${author.email}</td>
                <td><a href="${author.website}">website</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:url value="/logout" var="logoutUrl" />
    <p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>
</div>

<script>
    $('.dropdown-toggle').dropdown();

    $('#add_asset').click(function(){
        window.location.href='/asset_add_page';
    });

    $('#add_author').click(function(){
        window.location.href='/author_add_page';
    });

    $('#authorization_user').click(function () {
        window.location.href='/login';
    })

    $('#users_page').click(function () {
        window.location.href='/admin/users'
    })
</script>
</body>
</html>
