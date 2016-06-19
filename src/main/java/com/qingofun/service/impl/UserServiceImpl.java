package com.qingofun.service.impl;

import com.qingofun.dao.UserMapper;
import com.qingofun.pojo.User;
import com.qingofun.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qinkang on 16/6/19.
 */
@Service("userService")
public class UserServiceImpl implements IUserService{
    @Resource
    private UserMapper userMapper;
    public User getUserById(int userId) {
        return this.userMapper.selectByPrimaryKey(userId);
    }
}
