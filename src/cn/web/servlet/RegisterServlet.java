package cn.web.servlet;

import cn.blog.ResultInfo;
import cn.blog.User;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        //封装数据
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());
        //校验验证码
        String checkcode = request.getParameter("check_code");
        HttpSession session = request.getSession();
        String sessionCheckCode = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");
        if(sessionCheckCode==null || !sessionCheckCode.equalsIgnoreCase(checkcode)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //序列化resultinfo对象
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //调用server
        UserService service = new UserServiceImpl();
        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo();
        //相应结果
        if(flag) {
            //成功
            info.setFlag(true);
        } else {
            //失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
