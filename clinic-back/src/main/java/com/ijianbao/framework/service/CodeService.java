package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.Code;
import com.ijianbao.framework.mapper.CodeMapper;

@Service
@Transactional
public class CodeService {

	@Autowired
	 CodeMapper codeMapper;
	
	public int saveCode(Code code){
		 return codeMapper.saveCode(code);
	}
	
	public List<Code> list(){
		return codeMapper.list();
		
	}
	
	public Code getCodeByCid(int cid){
		return codeMapper.getCodeByCid(cid);
	}
	
	public int updateCode(Code code){
		return codeMapper.updateStatus(code);
	}
	
}
