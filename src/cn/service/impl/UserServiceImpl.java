package cn.service.impl;

import cn.blog.User;
import cn.dao.UserDao;
import cn.dao.impl.UserDaoImpl;
import cn.service.UserService;
import cn.utils.MailUtil;
import cn.utils.UuidUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User loginUser) {
        return dao.login(loginUser);
    }

    @Override
    public boolean register(User registerUser) {
        User u = dao.findByUsername(registerUser.getUsername());
        if(u!=null) {
            return false;
        }
        registerUser.setCode(UuidUtil.getUuid());
        registerUser.setStatus("N");
        dao.save(registerUser);

        try {
            MailUtil.sendActiveMail(registerUser.getAddress(),registerUser.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean findByUsername(String username) {
        User name = dao.findByUsername(username);
        if(name != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if(user != null) {
            dao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }


}
