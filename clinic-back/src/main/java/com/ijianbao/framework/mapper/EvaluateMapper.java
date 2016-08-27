package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.Evaluate;
import com.ijianbao.framework.bean.EvaluateAndMobile;

public interface EvaluateMapper {
  public List<Evaluate>  list();
  
  public List<EvaluateAndMobile> mobilelist();
}
