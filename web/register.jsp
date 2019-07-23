<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>G_Blue  |  Register</title>
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="css/custom.css " rel="stylesheet" type="text/css" >
  <script src="js/jquery-3.3.1.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/custom.main.js"></script>
  <script src="js/checkForm.js"></script>
</head>
<body>
<div class = "login_form container">
  <form id="registerForm" >
    <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email" name="address">
    </div>
    <div class="form-group">
      <label for="exampleInputUsername">Username</label>
      <a  id ="username_err" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
        <input type="text" class="form-control" id="exampleInputUsername" placeholder="Username" name="username">
      </a>
    </div>
    <div class="form-group">
      <label for="exampleInputPassword">Password</label>
      <a  id ="password_err" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
        <input type="password" class="form-control" id="exampleInputPassword" placeholder="Password" name="password">
      </a>
    </div>
    <div class="form-group">
      <label for="exampleInputConfirm">Confirm password</label>
      <a  id ="confirmPassword_err" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
      <input type="password" class="form-control" id="exampleInputConfirm" placeholder="Password" name = confirm_password>
      </a>
    </div>
    <div class="form-group">
      <label for="exampleInputDate">Birthday</label>
      <a  id ="birthday_err" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
      <input type="date" id="exampleInputDate" class="form-control" name="birthday" >
      </a>
    </div>
    <div class="form-group">
      <label >性别</label>
      <label class="radio-inline">
        <input type="radio" value="男" name="sex">
        <span>男</span>
      </label>
      <label class="radio-inline">
        <input type="radio" value="女" name="sex">
        <span>女</span>
      </label>
    </div>
    <div class="input-group form-group">
      <label for="exampleInputCheck" class="sr-only">Check code</label>
      <a  id ="checkCode_err" tabindex="0" role="button" data-toggle="popover" data-placement="left" data-trigger="focus" title="" data-content="">
      <input type="text" class="form-control" aria-describedby="Code" id="exampleInputCheck" placeholder="Check code" name="check_code">
      </a>
      <span class="input-group-btn">
        <button class="btn btn-default check_code_btn" type="button" style="background:url(${pageContext.request.contextPath}/checkCodeServlet?) no-repeat"></button>
      </span>
    </div>
    <a  id ="submit_err" tabindex="0" role="button" data-toggle="popover" data-placement="bottom" data-trigger="focus" title="提示" data-content="">
    <button type="submit" class="btn btn-default btn-group-justified">Submit</button>
    </a>
  </form>
</div>
</body>
</html>
