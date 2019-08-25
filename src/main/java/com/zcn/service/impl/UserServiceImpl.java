package com.zcn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcn.dao.UserDao;
import com.zcn.pojo.User;
import com.zcn.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}

	@Override
	public User queryUserByOpenid(String openid) {
		// TODO Auto-generated method stub
		return userDao.queryUserByOpenid(openid);
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return userDao.queryAll();
		
	}

	@Override
	public List<User> queryNoteq() {
		// TODO Auto-generated method stub
		return userDao.queryNoteq();
	}

}
