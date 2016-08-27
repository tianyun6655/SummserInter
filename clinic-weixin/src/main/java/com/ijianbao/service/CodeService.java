package com.ijianbao.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.mapper.CodeMapper;
import com.ijianbao.model.Code;

@Service
@Transactional
public class CodeService {
	
	@Autowired
    private CodeMapper codeMapper;
	
	public int countMobile(String mobile) {
		return codeMapper.countMobile(mobile);
	}
	
	public Code getCodeByCode(Code code) {
		code = codeMapper.getCodeByCode(code);
		return code;
	}
	
	public Code getCode(String mobile) {
		Code code = codeMapper.getCode(mobile);
		return code;
	}
	
	public Code generateCode(Code code) {
		int randomCode = (int)((Math.random()*9+1)*100000);
		code.setCode(String.valueOf(randomCode));
		Date now = new Date();
		code.setCreateTime(now);
		Date afterDate = new Date(now .getTime() + 5*60000);
		code.setInvalidTime(afterDate);
		codeMapper.generateCode(code);
		return code;
	}
	
	public int updateStatus(Code code) {
		return codeMapper.updateStatus(code);
	}
}
