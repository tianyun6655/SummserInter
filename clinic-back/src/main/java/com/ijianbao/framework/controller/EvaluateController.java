package com.ijianbao.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.Evaluate;
import com.ijianbao.framework.bean.EvaluateAndMobile;
import com.ijianbao.framework.service.EvaluateService;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    
	@Autowired
    EvaluateService evaluateService;
	@RequestMapping("/list")
	public ModelAndView list(){
		List<EvaluateAndMobile> list = evaluateService.mobilelist();
		
		return new ModelAndView("/evaluate/list","list",list);
		
	}
}
