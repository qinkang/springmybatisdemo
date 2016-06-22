package com.qingofun.service.impl;

import com.qingofun.dao.UserMapper;
import com.qingofun.pojo.User;
import com.qingofun.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by qinkang on 16/6/19.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(int userId) {

        return this.userMapper.selectByPrimaryKey(userId);
    }

    public List<User> getAllUsers() {
        return this.userMapper.queryAllUsers();
    }

    public List<Map<String, Object>> getAllUsers1() {
        return this.userMapper.queryAllUsers1();
    }

    public List<Map<String, Object>> getAllUsers2() {
        return this.userMapper.queryAllUsers2();
    }

    public List<Map<String, Object>> getUsersByCre(User user) {
        return this.userMapper.queryUsersByCre(user);
    }

    public List<User> getUsersByCre1(User user) {
        return this.userMapper.queryUsersByCre1(user);
    }
}
