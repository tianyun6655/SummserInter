package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.Code;

public interface CodeMapper {
	
  public int saveCode(Code coe);
  
  public List<Code> list();
  
  public Code getCodeByCid(int cid);
  
  public int updateStatus(Code code);
  
}
