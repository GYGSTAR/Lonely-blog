window.onload = function () {
    var code_btn = document.getElementsByClassName("check_code_btn")[0];
    code_btn.onclick = function () {
        var date = new Date().getTime();
        var url = getComputedStyle( document.getElementsByClassName("check_code_btn")[0],null).getPropertyValue("background-image");
        var URL = url.substr(0,url.length-2);
        code_btn.style.background = URL +date + ")";
    };





};
