
package com.ijianbao.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ijianbao.course.pojo.SNSUserInfo;
import com.ijianbao.course.pojo.WeixinOauth2Token;
import com.ijianbao.course.util.Analysis;
import com.ijianbao.model.Code;
import com.ijianbao.model.User;
import com.ijianbao.service.CodeService;
import com.ijianbao.service.UserService;
import com.ijianbao.util.AdvancedUtil;
import com.ruanwei.interfacej.SmsClientSend;

@Controller
@RequestMapping("/code")
public class CodeController {

	private Logger logger = Logger.getLogger(CodeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CodeService codeService;

	@RequestMapping("/form")
	public ModelAndView form(HttpServletRequest request, RedirectAttributes redirect) {
		ModelAndView model = null;
		String code = request.getParameter("code");
	//	String openId = checkCode(code);
		String openId = code;
		if (StringUtils.isEmpty(openId)) {
			model = new ModelAndView("error");
			return model;
		}
		User user = userService.getUserByOpenId(openId);
		if (null != user && user.getStatus() != 0) {
			redirect.addAttribute("uid", user.getUid());
		//	return "redirect:/card/form";
			model = new ModelAndView("/index");
			model.addObject("uid",user.getUid());
			return model;
		}
		redirect.addAttribute("openid", openId);
		model = new ModelAndView("redirect:/code/input");
		return model;
	}

	@RequestMapping("/input")
	public String input(@ModelAttribute Code code, @RequestParam("openid") String openid) {
		return "code/form";
	}

	@ResponseBody
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(@RequestParam("param") String param) {
		System.out.println(param);
		String[] params = param.split(" ");
		String mobile = params[0];
		String openid = params[1];
		String ret = "0";
		User user = userService.getUser(mobile);
		if (null == user) {
			user = new User();
			user.setMobile(mobile);
			user.setOpenid(openid);
			user = userService.save(user);
		}
		Code code = codeService.getCode(mobile);
		int count = codeService.countMobile(mobile);
		if (null == code && count < 5) {
			code = new Code();
			code.setMobile(mobile);
			code.setType(1);
			code = codeService.generateCode(code);
			ret = String.valueOf(sendSms(code));
		}
		return ret;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid Code code, BindingResult result, RedirectAttributes redirect) {
		Date date = new Date();
		if (result.hasErrors()) {
			return new ModelAndView("code/form", "error", result.getAllErrors());
		}
		Code cd = codeService.getCodeByCode(code);
		if (null == cd) {
			return new ModelAndView("code/form", "error", "验证码错误");
		}
		if (cd.getInvalidTime().before(date)) {
			return new ModelAndView("code/form", "error", "验证码过期");
		}
		redirect.addAttribute("mobile", code.getMobile());
		User user = userService.getUser(code.getMobile());
		if (null == user) {
			return new ModelAndView("code/form", "error", "user not exist");
		}
		user.setStatus(1);
		userService.updateStatus(user);
		redirect.addAttribute("uid", user.getUid());
		//return new ModelAndView("redirect:/card/form");
		ModelAndView model = new ModelAndView("/index");
		model.addObject("uid",user.getUid());
		return model;
		
	}
	
	

	private String checkCode(String code) {
		String ret = "";
		if (!"authdeny".equals(code)) {
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wx1d7d062ea22847fc",
					"ab5dce714d1f6026a39c0b5fddf4d253", code);
			String accessToken = weixinOauth2Token.getAccessToken();
			String openId = weixinOauth2Token.getOpenId();
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
			if (null != snsUserInfo) {
				ret = openId;
			}
		}
		return ret;
	}

	private int sendSms(Code code) {
		StringBuffer str = new StringBuffer();
		str.append("验证码：").append(code.getCode()).append("，5分钟内有效（感谢您注册保管家，如非本人操作请忽略）保管家统一服务热线：4008679090");
		String smsRetCode = SmsClientSend.sendSms("http://115.28.209.21:8888/sms.aspx", "733", "hongwei106", "1qaz2wsx",
				code.getMobile(), str.toString());
		code = Analysis.xmlElements(smsRetCode, code);
		codeService.updateStatus(code);
		return code.getStatus();
	}
}
