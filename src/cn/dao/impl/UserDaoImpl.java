package cn.dao.impl;

import cn.blog.User;
import cn.dao.UserDao;
import cn.utils.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSourse());

    //查询所有用户
    @Override
    public List<User> findAll() {
        //调用dao
        try {
            String sql = "select  * from user";
            List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
            return users;
        } catch (DataAccessException e) {
            return null;
        }
    }

    //按用户名查询
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException ignored) {}
        return user;
    }

    //登录
    @Override
    public User login(User loginUser) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), loginUser.getUsername(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }

    //注册
    @Override
    public void save(User registerUser) {
        String sql = "insert into user values(null,?,?,?,?,?,?,?)";
        template.update(sql, registerUser.getUsername(), registerUser.getPassword(),
                registerUser.getSex(), registerUser.getAddress(), registerUser.getBirthday(),
                registerUser.getCode(),registerUser.getStatus());
    }

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update user set status = 'Y' where code = ? ";
        template.update(sql,user.getCode());
    }
}