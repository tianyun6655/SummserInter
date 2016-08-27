package com.ijianbao.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.mapper.UserMapper;
import com.ijianbao.model.User;


@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    public User getUserByOpenId(String openid) {
    	
		return userMapper.getUserByOpenId(openid);
	}
    
    public User getUser(String mobile) {
		return userMapper.getUser(mobile);
	}
    
    public User save(User user) {
    	String mobile = user.getMobile();
    	System.out.println(mobile);
    	User us = userMapper.getUser(mobile);
    	System.out.println("US");
    	System.out.println(us);
    	if (null == us) {
    		user.setTime(new Date());
    		userMapper.saveUser(user);
    		us = user;
    		System.out.println("save");
    	} else {
    		if (StringUtils.isEmpty(us.getOpenid())) {
    			user.setUid(us.getUid());
    			userMapper.updateUser(user);
        		System.out.println("update");

    		}
    	}
		return us;
	}
    
    public int updateStatus(User user) {
    	return userMapper.updateStatus(user);
    }
}
