package com.ijianbao.framework.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.User;
import com.ijianbao.framework.service.UserService;
import com.sun.research.ws.wadl.Request;

@Controller
@RequestMapping("/user")
public class UserController {
  
	@Autowired
	UserService userService;

	@RequestMapping("/list")
	public ModelAndView list(){
		List<User> list = userService.list();

		return new ModelAndView("user/list","list",list);
	}
  
	@RequestMapping("/detail")
	public ModelAndView detail(@ModelAttribute User usr,@RequestParam("uid") int uid){
		User tmep = userService.getUserByUid(uid);
		return new ModelAndView("/user/form","user",tmep);
	}
	
	@RequestMapping(value="/updateUser",method = RequestMethod.POST)
	public String updateUser(@Valid User user){
		userService.updateUser(user);
		return "redirect:/user/list";
	}

}
