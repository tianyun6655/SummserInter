package com.ijianbao.framework.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.Dictionary;
import com.ijianbao.framework.bean.SubDictionary;
import com.ijianbao.framework.service.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
   @Autowired
   DictionaryService dictionaryService;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		List<Dictionary> list = dictionaryService.list();
		return new ModelAndView("dictionary/list","list",list);
	}
	
	@RequestMapping("/sublist")
	public ModelAndView subList(@RequestParam("id")int dId){
		List<SubDictionary> list = dictionaryService.sublist(dId);
		ModelAndView model = new ModelAndView("dictionary/sublist");
		model.addObject("list",list);
		model.addObject("dId",dId);
		return model;
	}

	@RequestMapping("/add_sub")
	public String add_sub(@RequestParam("dId")int dId,@ModelAttribute SubDictionary subDictionary){
		System.out.println(dId);
		subDictionary.setdId(dId);
		return "/dictionary/subform";
	}
	@RequestMapping("input_sub")
	public String input_sub(@Valid SubDictionary subDictionary){
		System.out.println(subDictionary.getdId());
		subDictionary.setCreatedBy(1);
		subDictionary.setCreatedTime(new Date());
		subDictionary.setUpdatedBy(1);
		subDictionary.setUpdatedTime(new Date());
		dictionaryService.inputSub(subDictionary);
		return "redirect:/dictionary/sublist?id="+subDictionary.getdId();
	}
	 
	@RequestMapping("/add")
	public String add_sub(@ModelAttribute Dictionary Dictionary){
		return "/dictionary/form";
	}
	
	@RequestMapping("input")
	public String input_sub(@Valid Dictionary dictionary){
		dictionary.setCreateBy(1);
		dictionary.setCreateTime(new Date());
		dictionary.setUpdateBy(1);
		dictionary.setUpdateTime(new Date());
		dictionaryService.input(dictionary);
		return "redirect:/dictionary/list";
	}
	 
	
	@ResponseBody
	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam("param")String param){
		System.out.println(param);

		String[] array = param.split(" ");
		int id = Integer.parseInt(array[0]);
		int changeToStatus = Integer.parseInt(array[1]);
		Dictionary tempDictionary = dictionaryService.getDictionaryById(id);
		tempDictionary.setStatus(changeToStatus);
		dictionaryService.updateStatus(tempDictionary);
		if(changeToStatus==0){
			return "0";
		}else{
			return "1";
		}
		
	}
}
