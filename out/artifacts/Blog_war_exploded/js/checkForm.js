function checkUsername(){
    //获取用户名的值
    var username = $("#exampleInputUsername").val();
    var reg_username = /^\w{8,20}$/;
    var flag = reg_username.test(username);
    var user_err = document.getElementById("username_err");
    if(flag) {
        //发送ajax请求
        $.get("findUsernameServlet",{username:username},function (data) {
            if(data.search("true") !== -1){
                //用户名存在
                user_err.title = "警告";
                user_err.dataset.html = true;
                user_err.dataset.content = "此用户名已注册，"+"<a href=' "+data.replace("true","") + "/login.jsp"+"'>"+"是否登录？"+"</a>";
                $('#username_err').popover("show");
                setTimeout(function () { $('#username_err').popover("hide"); }, 40000);
                flag= false;
            } else {
                flag = true;
                setTimeout(function () { $('#username_err').popover("hide"); }, 10000);
            }
        });
    } else {
        //用户名非法
        user_err.title = "警告";
        user_err.dataset.content = "用户名不合法";
       $('#username_err').popover("show");
       flag = false;
    }
    return flag;
}


function checkPassword() {
    var password = $("#exampleInputPassword").val();
    var reg_password = /^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])).{6,12}$/;
    var flag = reg_password.test(password);
    if(flag) {
        setTimeout(function () { $('#password_err').popover("hide"); }, 4000);
        flag =  true;
    } else {
        //用户名非法
        var password_err = document.getElementById("password_err");
        password_err.dataset.content = "密码中必须含有小写字母、大写字母、数字且密码长度为6-12位";
        password_err.dataset.title = "警告";
        $('#password_err').popover("show");
        flag = false;
    }
    return flag;
}
function checkConfirmcheckPassword() {
    var flag =false;
    var confirmPassword = $("#exampleInputConfirm").val();
    var password = $("#exampleInputPassword").val();
    if(confirmPassword === password) {
        setTimeout(function () { $('#confirmPassword_err').popover("hide"); }, 4000);
        flag= true;
    } else {
        var confirmPassword_err = document.getElementById("confirmPassword_err");
        confirmPassword_err.dataset.content = "两次输入的密码不一致";
        confirmPassword_err.dataset.title = "警告";
        $('#confirmPassword_err').popover("show");
        flag= false;
    }
    return flag;
}
function checkBirthday() {
    var flag = false;
    var birthday = $("#exampleInputDate").val();
    if(birthday === "") {
        var birthday_err = document.getElementById("birthday_err");
        birthday_err.dataset.content = "生日不能为空";
        birthday_err.dataset.title = "警告";
        $('#birthday_err').popover("show");
        flag = false;
    } else {
        setTimeout(function () { $('#birthday_err').popover("hide"); }, 4000);
        flag = true;
    }
    return flag;
}
function checkCode() {
    var flag = true;
    var checkcode = $("#exampleInputCheck").val();
            if(checkcode === "") {
                var checkCode_err = document.getElementById("checkCode_err");
                checkCode_err.dataset.content = "验证码不能为空";
                checkCode_err.dataset.title = "警告";
                $('#checkCode_err').popover("show");
                setTimeout(function () { $('#checkCode_err').popover("hide"); }, 4000);
                flag = false;
            }
    return flag;
}
$(function () {
    $("#registerForm").submit(function () {
       // alert(checkUsername() +""+ checkPassword() +""+ checkConfirmcheckPassword() +""+ checkCode() +""+ checkBirthday());
        if(checkUsername() && checkPassword() && checkConfirmcheckPassword() && checkCode() && checkBirthday()){
            $.post("registerServlet",$(this).serialize(),function (data) {
                var submit_err = document.getElementById("submit_err");
                    if(data.flag){
                        submit_err.dataset.content = "注册成功";
                        $('#submit_err').popover("show");
                        return true;
                    } else {
                        if(data.errorMsg==="验证码错误") {
                            submit_err.dataset.content = "验证码错误";
                            $('#submit_err').popover("show");
                        } else {
                            submit_err.dataset.content = "注册失败";
                            $('#submit_err').popover("show");
                        }
                    }
            });
        }
        return false;
    });
    $("#exampleInputUsername").blur(checkUsername);
    $("#exampleInputPassword").blur(checkPassword);
    $("#exampleInputConfirm").blur(checkConfirmcheckPassword);
    $("#exampleInputDate").blur(checkBirthday);
    $("#exampleInputCheck").blur(checkCode);
});