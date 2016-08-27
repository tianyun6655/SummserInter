package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.Dictionary;
import com.ijianbao.framework.bean.SubDictionary;
import com.ijianbao.framework.mapper.DictionaryMapper;

@Service
@Transactional
public class DictionaryService {

	@Autowired
	DictionaryMapper dictionMapper;
	
	
	public List<Dictionary> list(){
		return dictionMapper.list();
	}
	
	public Dictionary getDictionaryById(int id){
		return dictionMapper.getDictionaryById(id);
	}
	
	public int updateStatus(Dictionary dictionary){
		return dictionMapper.updateStatus(dictionary);
	}
	
	public List<SubDictionary> sublist(int dId){
		return dictionMapper.sublist(dId);
	}
	
	public int inputSub(SubDictionary subDictionary){
		return dictionMapper.inputSub(subDictionary);
	}
	
	public int input(Dictionary dictionary){
		return dictionMapper.input(dictionary);
	}
}
