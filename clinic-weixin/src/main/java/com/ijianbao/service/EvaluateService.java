package com.ijianbao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.mapper.EvaluateMapper;
import com.ijianbao.model.Evaluate;

@Service
@Transactional
public class EvaluateService {
	
	@Autowired
    private EvaluateMapper evaluateMapper;
	
	public int saveEvaluate(Evaluate evaluate) {
		return evaluateMapper.saveEvaluate(evaluate);
	}
	
}
