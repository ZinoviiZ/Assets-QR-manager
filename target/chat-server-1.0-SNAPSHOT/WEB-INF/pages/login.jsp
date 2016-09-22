<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <style>
        .centered {
            margin-top: 50%;
        }
        .container {
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row" id="pwd-container">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <section class="login-form centered">
                <form method="post" action="/j_spring_security_check" role="login">
                    <img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" />
                    <input type="text" name="j_login" placeholder="login" required class="form-control input-lg container" value="" />
                    <input type="password" name="j_password" class="form-control input-lg container" id="password" placeholder="Password" required="" />
                    <button type="submit" name="go" class="btn btn-lg btn-primary btn-block container">Sign in</button>
                    <div>
                        <a href="/login/registration">Create account</a>
                    </div>
                </form>
                <c:if test="${param.error ne null}">
                    <p style="color:#720b13;">Wrong login or password!</p>
                </c:if>

                <c:if test="${param.logout ne null}">
                    <p style="color:#2019a0;">Chao!</p>
                </c:if>
                <div class="form-links">
                    <a href="https://vk.com/id24223960">Need Help? Contact Us!</a>
                </div>
            </section>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>