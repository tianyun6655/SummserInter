package com.ijianbao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.mapper.CardMapper;
import com.ijianbao.model.Card;

@Service
@Transactional
public class CardService {
	
	@Autowired
    private CardMapper cardMapper;
	
	public Card getCardByKey(int cid) {
		return cardMapper.getCardByKey(cid);
	}
	
	public Card getCard(Card card) {
		return cardMapper.getCard(card);
	}
	
	public int updateCard(Card card) {
		return cardMapper.updateCard(card);
	}
	public int getCardType(Card card){
		return cardMapper.getCardType(card);
	}
}
