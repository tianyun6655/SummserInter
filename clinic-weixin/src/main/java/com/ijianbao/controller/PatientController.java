package com.ijianbao.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.course.pojo.SNSUserInfo;
import com.ijianbao.course.pojo.WeixinOauth2Token;
import com.ijianbao.model.Card;
import com.ijianbao.model.Patient;
import com.ijianbao.model.User;
import com.ijianbao.service.CardService;
import com.ijianbao.service.PatientService;
import com.ijianbao.service.UserService;
import com.ijianbao.util.AdvancedUtil;


@Controller
@RequestMapping("/patient")
public class PatientController {

    private Logger logger = Logger.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CardService cardService;
    
    private Date date = new Date();
    
    @RequestMapping("/form")
    public String createForm(@ModelAttribute Patient patient, @RequestParam("cid") int cid,@RequestParam("productType")int type ) {
    	
        return "patient/form";
    }
    
    @RequestMapping("/zwy_form")
    public String createZhenwuyouForm(@ModelAttribute Patient patient, @RequestParam("cid") int cid,@RequestParam("productType")int type){
    	return "patient/zwy_form";
    }
    @RequestMapping("/azt_form")
    public String createAnzhenTongForm(@ModelAttribute Patient patient, @RequestParam("cid") int cid,@RequestParam("productType")int type){
    	return "patient/azt_form";
    }
    
    @RequestMapping(value="/input", method = RequestMethod.POST)
    public ModelAndView input(@Valid Patient patient) {
    	System.out.println("the patient type is: "+patient.toString());
    	patient.setStatus(1);
    	patient.setCreateTime(date);
    	patient.setUpdateTime(date);
    	if(patient.getIsRegister()==2)
    	{
    		patient.setIsRegister(0);
    	}
    	Card card = cardService.getCardByKey(patient.getCid());
    	if (card == null) {
    		
    		return new ModelAndView("without","error","此卡已经被使用！");
    	}
    	patientService.savePatient(patient);

    	card.setStatus(3);
    	cardService.updateCard(card);
    	// todo 更新卡状态
        return new ModelAndView("patient/success");
    }
    
    @RequestMapping(value="/list")
    public ModelAndView list(HttpServletRequest request) {
    	String code = request.getParameter("code");
	//	String openId = checkCode(code);
        String openId="111111";
		if (StringUtils.isEmpty(openId)) {
			return new ModelAndView("error");
		}
		User user = userService.getUserByOpenId(openId);
		if (null == user) {
			return new ModelAndView("without","error","您还没有预约任何服务！             请尝试预约体验服务。");
		}
		List<Patient> patients = patientService.getPatient(user.getUid());
		System.out.println(patients.size());
		if (patients.size() == 0) {
			return new ModelAndView("without","error","您还没有预约任何服务！             请尝试预约体验服务。");
		}
        return new ModelAndView("patient/list", "patients", patients);  
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
    
    @RequestMapping(value="/test")
    public ModelAndView list() {
			return new ModelAndView("without","error","您还没有预约任何服务！             请尝试预约体验服务。");
		
    }
}


