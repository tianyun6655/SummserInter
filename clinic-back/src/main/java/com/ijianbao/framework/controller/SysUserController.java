package com.ijianbao.framework.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.SysUser;
import com.ijianbao.framework.service.SysUserService;


@Controller
@RequestMapping("/sysuser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	private Date date = new Date();
	
	@RequestMapping(value="/form")
	public String addPerson(@ModelAttribute SysUser sysUser){
		return "sys/userform";
	}
	

	
   @RequestMapping(value="/add", method=RequestMethod.POST)
   public ModelAndView saveSysUser(@Valid SysUser sysUser){
	   SysUser user = sysUserService.getUserByMobile2(sysUser.getMobile());
	   if(user!=null){
		
		   return new ModelAndView("sys/userform","error","此电话号码已经存在！");
	   }
	   sysUser.setCreateTime(date);
	   sysUser.setUpdateTime(date);
	   // todo 取当前用户编号
	   sysUser.setCreateUser(1);
	   sysUser.setUpdateUser(1);
	   sysUserService.saveSysUser(sysUser);   
	   return new ModelAndView("redirect:/sysuser/list");
   }
   
   @RequestMapping(value="/list")
   public ModelAndView list(){
	   List<SysUser> list = sysUserService.list();
	   return new ModelAndView("sys/userlist","list",list);
   }
   
   @RequestMapping("/detail")
   public ModelAndView createDetail(@ModelAttribute SysUser sysUser,@RequestParam("id") int id){
         SysUser newUser = sysUserService.getSysUser(id);
         if(newUser == null){
        	 return new ModelAndView("sys/userdetail","error","当前用户不存在！");
         }
         return new ModelAndView("sys/userdetail","sysUser",newUser);
   }
   
   
   @RequestMapping(value="/update",method=RequestMethod.POST)
   public ModelAndView update(@Valid SysUser sysUser) {
	   if(sysUser == null){
		   return new ModelAndView("sys/userdetail","error","当前用户不存在！");
	   }
	   sysUserService.updateSysUser(sysUser);
	   return new ModelAndView("redirect:/sysuser/list");
   }

}
