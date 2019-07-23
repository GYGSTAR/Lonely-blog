package cn.web.servlet;

import cn.service.UserService;
import cn.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println(code);
        String realPath = this.getServletContext().getRealPath("login.jsp");
        if(code != null) {
            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);
            String msg = null;
            if(flag) {
                msg = "<a href=\"" + realPath +"\">激活成功,请登录</a>";
            } else {
                msg = "请稍后重试，或联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        } else {
            response.getWriter().write("请不要作");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
