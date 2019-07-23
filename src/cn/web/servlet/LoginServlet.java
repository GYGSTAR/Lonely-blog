package cn.web.servlet;

import cn.blog.User;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
        protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            UserService service = new UserServiceImpl();
            User loginUser = service.login(user);

            System.out.println(user.toString());

            CookiesCheck cookieChecher = new CookiesCheck();
            if(loginUser != null) {
                if(loginUser.getStatus().equals("Y")) {
                    System.out.println(loginUser.toString());
                    response.getWriter().write("登陆成功");
                } else {
                    response.getWriter().write("您的账号未激活，请检查您的邮箱，激活账号");
                }
            } else {
                int status = cookieChecher.Err_times(request);
                if(status>4) {
                    Cookie cok = cookieChecher.setCookie(request);
                    cok.setMaxAge(60*5);
                    response.addCookie(cok);
                    response.getWriter().write("密码错误，请稍后重试");
                } else if(status<0){
                    response.getWriter().write("密码错误次数太多，请稍后再试");
                } else {
                    response.getWriter().write("密码错误,请重试");
                    Cookie cook = cookieChecher.setCookie(request);
                    cook.setMaxAge(60*5);
                    response.addCookie(cook);
                }
            }
        }

        protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            this.doPost(request,response);
        }
}
