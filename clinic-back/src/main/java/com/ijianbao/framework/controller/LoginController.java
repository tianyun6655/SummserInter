package com.ijianbao.framework.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.SysUser;
import com.ijianbao.framework.service.SysUserService;

@Controller
public class LoginController {
	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
    private SysUserService sysUserService;
	
	@RequestMapping(value = "/")
	public String loginForm(@ModelAttribute SysUser sysUser) {
		return "login";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@Valid SysUser sysUser) {
		String password = sysUser.getPassword();
		sysUser = sysUserService.findSysUser(sysUser);
		if (null == sysUser) {
			return new ModelAndView("login", "error", "手机号不存在!");  
		}
		if (!password.equals(sysUser.getPassword())) {
			return new ModelAndView("login", "error", "密码不正确!");  
		}
		return new ModelAndView("index", "sysUser.id", sysUser.getId());
	}
}
