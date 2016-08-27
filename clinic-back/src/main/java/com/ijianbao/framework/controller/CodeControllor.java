package com.ijianbao.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.Code;
import com.ijianbao.framework.service.CodeService;
import com.ruanwei.interfacej.SmsClientSend;
import com.ruanwei.tool.Analysis;

@Controller
@RequestMapping("/code")
public class CodeControllor {
    
	@Autowired
	CodeService codeService;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		List<Code> list = codeService.list();
		return new ModelAndView("code/list","list",list);
	}
	
	
	@RequestMapping("/send")
	public ModelAndView send(@RequestParam("cid") int cid){
		
		Code code = codeService.getCodeByCid(cid);
		sendSms(code);
		
		return new ModelAndView("redirect:/code/list");
		
	}
	private int sendSms(Code code) {
		String smsRetCode = SmsClientSend.sendSms("http://115.28.209.21:8888/sms.aspx", "733", "hongwei106", "1qaz2wsx",
				code.getMobile(), code.getCode());
		code = Analysis.xmlElements(smsRetCode, code);
	    codeService.updateCode(code);
		return code.getStatus();
	}
}
