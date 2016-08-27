package com.ijianbao.framework.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ijianbao.framework.bean.Card;
import com.ijianbao.framework.service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {
	
	private Logger logger = Logger.getLogger(CardController.class);
	
	@Autowired
	private CardService cardService;
	
	private final int STATUS_SUCCESS = 1;
	private final int STATUS_LOST_EFFCACY = 2;
	private final int STATUS_CHECKING = 3;
	private final int STATUS_USED = 4;
	
	// @RequestMapping("generate")
	public String generate(@RequestParam("user") String user, @RequestParam("pwd") String pwd) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer str = new StringBuffer();
		String no = "100";
		String pro = "SZT";
		String qd = "PA";
		String date = "0621";
		str.append(no).append(pro).append(qd).append(date);
		for (int i = 0; i < 10; i++) {
			Card card = new Card();
			String pd = String.valueOf(((Math.random() * 9 + 1) * 100000));
			str.append(pd);
			card.setNo(str.toString());
			card.setPassword(pd);
			card.setStatus(1);
			try {
				card.setCreateTime(format.parse("2016-07-01 00:00:00"));
				card.setInvalidTime(format.parse("2016-09-31 23:59:59"));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return "user/form";
	}

	@RequestMapping("/checkForm")
	public String createCardReview(@ModelAttribute Card card) {
		return "card/checkForm";
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public ModelAndView cardCheck(@Valid Card card) {
		String password = card.getPassword();
		Card tarCard = cardService.getCardByNo(card);
		if (tarCard == null) {
			return new ModelAndView("card/checkForm", "error", "卡号不存在!");
		}
		if (!password.equals(tarCard.getPassword())) {
			return new ModelAndView("card/checkForm", "error", "密码错误!");
		}
		if (tarCard.getStatus() != 1) {
			return statusError(tarCard.getStatus());
		}
		if (compareDate(tarCard.getInvalidTime()) < 0) {
			return new ModelAndView("card/checkForm", "error", "此卡过期!");
		}
		ModelAndView model =null;
		if(tarCard.getType()==2||tarCard.getType()==3){
		  model = new ModelAndView("redirect:/patient/form");
		}else{
		  model = new ModelAndView("redirect:/patient/zhenwuyou_form");
		}
		model.addObject("cid",tarCard.getCid());
		model.addObject("cardtype",tarCard.getType());
		
		//return new ModelAndView("redirect:/patient/form", "cid", tarCard.getCid());
	    return model;
	}

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		List<Card> list = cardService.list();
		return new ModelAndView("card/list", "list", list);
	}

	private ModelAndView statusError(int checkNumber) {
		if (checkNumber == STATUS_LOST_EFFCACY) {
			return new ModelAndView("card/checkForm", "error", "此卡已经失效!");
		} else if (checkNumber == STATUS_CHECKING) {
			return new ModelAndView("card/checkForm", "error", "此卡还在审核中");
		} else if (checkNumber == STATUS_USED) {
			return new ModelAndView("card/checkForm", "error", "此卡已经被使用");
		}
		return new ModelAndView("card/checkForm", "error", "未知错误请联系工作人员");
	}

	private int compareDate(Date cardDate) {
		Date currentDate = new Date();
		int result = cardDate.compareTo(currentDate);
		return result;
	}

}
