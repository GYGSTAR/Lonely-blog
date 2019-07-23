package cn.service;

import cn.blog.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户管理接口
 */
public interface UserService {
    //查询所有用户信息
        List<User> findAll();
        User login(User loginUser);
        boolean register(User registerUser);
        boolean findByUsername(String username);
        boolean active(String code);
}
