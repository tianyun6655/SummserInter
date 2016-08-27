package com.ijianbao.mapper;

import com.ijianbao.model.Code;

public interface CodeMapper {
	
	public int countMobile(String mobile);
	
	public Code getCode(String mobile);
	
	
	public Code getCodeByCode(Code code);
	
	public int generateCode(Code code);
	
	public int updateStatus(Code code);
}
