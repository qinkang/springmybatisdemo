package com.qingofun.service;


import com.qingofun.pojo.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
	User getUserById(int userId);
	List<User> getAllUsers();
	List<Map<String, Object>> getAllUsers1();
	List<Map<String, Object>> getAllUsers2();
	List<Map<String, Object>> getUsersByCre(User user);
	List<User> getUsersByCre1(User user);
}
