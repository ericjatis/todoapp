package com.todoapp.dao;

import java.util.List;
import com.todoapp.model.User;
//EricHalim
//AbdulJalil1
public interface UserDAO {
	public void createUser(User user);
	public User findUserByUsername(String username);
	public List<User> findAllUser();
}
