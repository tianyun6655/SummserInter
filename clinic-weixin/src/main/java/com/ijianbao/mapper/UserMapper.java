package com.ijianbao.mapper;

import com.ijianbao.model.User;

public interface UserMapper {
	
	public User getUserByOpenId(String openid);
	
	public User getUser(String mobile);
	
	public int saveUser(User user);
	
	public int updateStatus(User user);
	
	public int updateUser(User user);

}
