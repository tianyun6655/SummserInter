package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.User;

public interface UserMapper {
	
	public int saveUser(User user);

	public User getUserByMobile(String mobile);
	
	public List<User> list();
	
	public User getUserByUid(int uid);

	public int updateUser(User user);

}
