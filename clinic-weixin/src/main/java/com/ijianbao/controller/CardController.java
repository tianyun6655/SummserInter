package com.ijianbao.controller;

import java.util.Date;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ijianbao.model.Card;
import com.ijianbao.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {
	
	private Logger logger = Logger.getLogger(CardController.class);
	
	@Autowired
    private CardService cardService;
	
	@RequestMapping("/form")
    public String createForm(@ModelAttribute Card card,@RequestParam("type")int type) {
		System.out.println(type);
		card.setType(type);
        return "card/form";
    }
	
	@RequestMapping(value="/input", method = RequestMethod.POST)
	public ModelAndView input(@Valid Card card, RedirectAttributes redirect) {
		int uid = card.getUid();
		int type =card.getType();
		System.out.println("the inital type is: "+type+" the uid is: "+uid);
		String password = card.getPassword();
		card = cardService.getCard(card);
		if (null == card) {
			return new ModelAndView("card/form", "error", "卡号不存在");
		}
		if (card.getInvalidTime().before(new Date())){
			return new ModelAndView("card/form", "error", "卡片已经过期");
		}
		if (!password.equals(card.getPassword())) {
			return new ModelAndView("card/form", "error", "密码不正确");
		}
		if(type!=card.getType()){
			return new ModelAndView("card/form", "error", "此卡片无法使用此服务");

		}
		card.setUid(uid);
		cardService.updateCard(card);
		//redirect.addAttribute("cid", card.getCid());
		//return new ModelAndView("redirect:/patient/form");
		//redirect.addAttribute("type",card.getType());
		redirect.addAttribute("cid", card.getCid());
		redirect.addAttribute("type",card.getType());
		ModelAndView model = new ModelAndView("redirect:/card/tocard");
		return model;

	}
	
	@RequestMapping(value="/index")
	public String indexpage(){
		return "/index";
	}
	@RequestMapping("/tocard")
	public ModelAndView toCard( @RequestParam("cid")int cid,@RequestParam("type")int type,RedirectAttributes redirect){	
		System.out.println("cid is: " +cid);
		System.out.println("type is: "+ type);
		redirect.addAttribute("cid", cid);
		redirect.addAttribute("productType",type);
		if(type==1){
		return new ModelAndView("redirect:/patient/zwy_form");
		}else if(type==2){
			return new ModelAndView("redirect:/patient/form");

		}else if(type==3){
			return new ModelAndView("redirect:/patient/azt_form");

		}
		return new ModelAndView("error");

	}
	
	@RequestMapping("/describe")
    public String describe(@RequestParam("type") int type) {
		
		System.out.println(type);
		if(type==1){
		 return "card/zwy_des"; 
		 }
		else if (type==2){ 
	     return "card/describe";
	     }
		else{
		 return "card/azt_des";
		}
    }
	
}
