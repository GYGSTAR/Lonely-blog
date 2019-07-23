<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>G_Blue  ||  Login</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/custom.css " rel="stylesheet" type="text/css" >
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/login.js"></script>
</head>
<body>
    <div class="login_form container">
        <div id="userImage">
            <img src="resource/image/head.jpg" alt="用户头像" class="img-circle">
        </div>
        <form id="loginForm">
            <div class="form-group">
                <label for="loginInputUsername">Username</label>
                <a  id ="loginUsername" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
                <input type="text" class="form-control" id="loginInputUsername" placeholder="Username" name="username">
                </a>
            </div>
            <div class="form-group">
                <label for="loginInputPassword">Password</label>
                <input type="password" class="form-control" id="loginInputPassword" placeholder="Password" name="password">
            </div>
            <a  id ="loginTips" tabindex="0" role="button" data-toggle="popover" data-placement="bottom" data-trigger="focus" title="提示" data-content="">
            <button type="submit" class="btn btn-default btn-group-justified" id="loginSubmit">Submit</button>
            </a>
        </form>
    </div>
</body>
</html>
