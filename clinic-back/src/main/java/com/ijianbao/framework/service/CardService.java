package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.Card;
import com.ijianbao.framework.mapper.CardMapper;

@Service
@Transactional
public class CardService {
	
	@Autowired
    private CardMapper cardMapper;
	
//	public int generateCard(Card card) {
//		return cardMapper.generateCard(card);
//	}
	
	
	public Card getCardByNo(Card card){
		return cardMapper.getCardByNo(card);
	}
	
	public Card getCardByCid(int cid){
		return cardMapper.getCardByCid(cid);
	}
	
	public int updateCardbyCid(Card card){
		return cardMapper.updateCardbyCid(card);
	}
	
	public List<Card> list(){
		return cardMapper.list();
	}
	
	public int bindUser(Card card){
		return cardMapper.bindUser(card);
	}
}
