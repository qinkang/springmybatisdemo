package com.qingofun.dao;

import com.qingofun.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> queryAllUsers();

    List<Map<String, Object>> queryAllUsers1();

    List<Map<String, Object>> queryAllUsers2();

    List<Map<String, Object>> queryUsersByCre(User user);

    List<User> queryUsersByCre1(User user);
}