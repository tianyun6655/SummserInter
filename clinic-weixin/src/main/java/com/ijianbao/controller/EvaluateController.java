package com.ijianbao.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ijianbao.course.pojo.SNSUserInfo;
import com.ijianbao.course.pojo.WeixinOauth2Token;
import com.ijianbao.model.Evaluate;
import com.ijianbao.model.User;
import com.ijianbao.service.EvaluateService;
import com.ijianbao.service.PatientService;
import com.ijianbao.service.UserService;
import com.ijianbao.util.AdvancedUtil;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

	private Logger logger = Logger.getLogger(EvaluateController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private EvaluateService evaluateService;
	
	@Autowired
	private PatientService patientService;
	
	@RequestMapping("/form")
	public ModelAndView form(HttpServletRequest request, RedirectAttributes redirect) {
		String code = request.getParameter("code");
//		String openId = "oLEWNjidWn4p2d8clvgb4fC-F7hE";
		//String openId = checkCode(code);
		String openId="111111";
		if (StringUtils.isEmpty(openId)) {
			return new ModelAndView("error");
		}
		User user = userService.getUserByOpenId(openId);
		if (null == user) {
			return new ModelAndView("without","error","您还没有预约任何服务！请尝试预约体验服务。");
		}
		int count = patientService.getPatient(user.getUid()).size();
		if (count <= 0) {
			return new ModelAndView("without","error","您还没有预约任何服务！请尝试预约体验服务。");
		}
		redirect.addAttribute("uid", user.getUid());
		return new ModelAndView("redirect:/evaluate/input");
	}

	@RequestMapping(value = "/input")
	public ModelAndView input(@RequestParam("uid")int uid) {
		Evaluate evaluate = new Evaluate();
		evaluate.setUid(uid);
		ModelAndView modelandView = new ModelAndView("evaluate/form");
		modelandView.addObject("evaluate",evaluate);
		return modelandView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@Valid Evaluate evaluate) {
		evaluate.setLevel(5);
		evaluate.setCreateTime(new Date());
		evaluateService.saveEvaluate(evaluate);
		return new ModelAndView("evaluate/success");
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
}
