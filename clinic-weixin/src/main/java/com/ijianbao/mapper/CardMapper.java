package com.ijianbao.mapper;

import com.ijianbao.model.Card;

public interface CardMapper {
	
	public Card getCardByKey(int cid);
	
	public Card getCard(Card card);
	
	public int updateCard(Card card);
	
	public int getCardType(Card card);
}
