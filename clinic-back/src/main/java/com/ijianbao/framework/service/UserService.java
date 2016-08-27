package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.User;
import com.ijianbao.framework.mapper.UserMapper;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public int saveUser (User user){
		return userMapper.saveUser(user);
		
	}
	
	public User getUserByMobile(String mobile){
		return userMapper.getUserByMobile(mobile);
	}
	
	public List<User> list(){
		return userMapper.list();
	}
	
	public User getUserByUid(int uid){
		return userMapper.getUserByUid(uid);
	}
	public int updateUser(User user){
		return userMapper.updateUser(user);
	}
}
