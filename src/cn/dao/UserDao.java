package cn.dao;

import cn.blog.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findByUsername(String username);
    User login(User loginUser);
    void save(User registerUser);
    User findByCode(String code);
    void updateStatus(User user);
}
