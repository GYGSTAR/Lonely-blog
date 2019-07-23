function loginUsername(){
    //获取用户名的值
    var username = $("#loginInputUsername").val();
        //发送ajax请求
    var loginUsername = document.getElementById("loginUsername");
    var flag = true;
    $.post("findUsernameServlet",{username:username},function (data) {
        if(data !=="true"){
            //用户不名存在
            loginUsername.title = "警告";
            loginUsername.dataset.html = true;
            loginUsername.dataset.content = "该用户未注册，"+"<a href='"+data + "/register.jsp"+"'>"+"是否注册？"+"</a>";
            $('#loginUsername').popover("show");
            flag= false;
        }
    });
    return flag;
}


$(function () {
    $("#loginForm").submit(function () {
        alert(loginUsername());
        if(loginUsername()) {
            $.post("loginServlet",$(this).serialize(),function (data) {
                console.log(data);
                if(data !==""){
                    var loginTips = document.getElementById("loginTips");
                    loginTips.dataset.content=data;
                    $("#loginTips").popover("show");
                }
            });
        }
        return false;
    });



    $("#loginInputUsername").blur(loginUsername);
});