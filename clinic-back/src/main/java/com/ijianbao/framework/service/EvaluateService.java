package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.Evaluate;
import com.ijianbao.framework.bean.EvaluateAndMobile;
import com.ijianbao.framework.mapper.EvaluateMapper;

@Service
@Transactional
public class EvaluateService {

	@Autowired
	EvaluateMapper evaluateMapper;
	
	public List<Evaluate>  list(){
		return evaluateMapper.list();
		
	}
	public List<EvaluateAndMobile> mobilelist(){
		return evaluateMapper.mobilelist();
	}
}
