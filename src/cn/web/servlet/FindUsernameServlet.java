package cn.web.servlet;

import cn.service.UserService;
import cn.service.impl.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

@WebServlet("/findUsernameServlet")
public class FindUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserService service = new UserServiceImpl();
        boolean usernamExist = service.findByUsername(username);
        String data = "true";
        if(usernamExist) {
            data += this.getServletContext().getContextPath();
            response.getWriter().write(data);
        } else {
            data = this.getServletContext().getContextPath();
            response.getWriter().write(data);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
