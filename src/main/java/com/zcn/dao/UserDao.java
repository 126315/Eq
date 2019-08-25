package com.zcn.dao;

import java.util.List;

import com.zcn.pojo.User;

public interface UserDao {
	public void insertUser(User user);
	public User queryUserByOpenid(String openid);
	public List<User> queryAll();
	public List<User> queryNoteq();
}
