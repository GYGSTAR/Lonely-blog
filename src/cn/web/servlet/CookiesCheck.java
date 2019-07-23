package cn.web.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookiesCheck {
    private static Cookie[] cookies;
    private static final  int ERROR_OVER =3;
    private  String Error = "err_times";

    /**
     * 判断Cookie记录的错误次数
     * 返回值为 int类型
     * 若返回0-4，表示错误的次数
     * 若返回负数，表示已经超出ERROR_OVER，限制登录
     */
    public int Err_times(HttpServletRequest req){
        cookies = req.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie: cookies) {
                String name = cookie.getName();
                System.out.println(name);
                if(Error.equals(name)) {
                    if(Integer.parseInt(cookie.getValue()) < ERROR_OVER) {
                        return Integer.parseInt(cookie.getValue());
                    } else {
                        //cookie设置5mins存活时间
                        cookie.setMaxAge(60*5);
                        return -10;
                    }
                }
            }
        }
        return 0;
    }
    public Cookie setCookie(HttpServletRequest req) {
        cookies = req.getCookies();
        Cookie cok = null;
        for (Cookie cookie: cookies) {
            String name = cookie.getName();
            if(Error.equals(name)) {
                int now_times = Integer.parseInt(cookie.getValue());
                now_times++;
                cookie.setValue(Integer.toString(now_times));
                return cookie;
            } else {
                cok = new Cookie(Error,"1");
            }
        }
        return cok;
    }
}
