package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.Card;

public interface CardMapper {
	
//	public int generateCard(Card card);
	
	public Card getCardByNo(Card card);
	
	public Card getCardByCid(int cid);
	
	public int updateCardbyCid(Card card);
	
	public List<Card> list();
   
	public int bindUser(Card card);
}


