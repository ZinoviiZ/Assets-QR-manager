<%--
  Created by IntelliJ IDEA.
  User: Zinoviy
  Date: 9/5/16
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
        .centered {
            margin-top: 40%;
        }
        .container {
            margin: 20px 0;
        }
    </style>
</head>
<body>
<div class="row" id="pwd-container">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <section class="login-form centered">
            <form method="post" action="/login/save" role="login">
                <img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" />
                <label for="name">Login:</label> <br>
                <input id="name" class="form-control input-lg" required type="text" value="login" name="login"><br>
                <label for="password">Password:</label> <br>
                <input id="password" class="form-control input-lg" type="password" value="password" name="password"><br>
                <label for="email">Emaill:</label> <br>
                <input id="email" class="form-control input-lg" type="email" value="sand@box.com" name="email">
                <button type="submit" name="go" class="btn btn-lg btn-primary btn-block container">Sign in</button>
            </form>
            <div class="form-links">
                <a href="https://vk.com/id24223960">Need Help? Contact Us!</a>
            </div>
        </section>
    </div>
    <div class="col-md-4"></div>
</div>
</body>

</html>
