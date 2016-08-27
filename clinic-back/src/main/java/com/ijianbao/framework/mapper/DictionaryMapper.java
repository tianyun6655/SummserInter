package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.Dictionary;
import com.ijianbao.framework.bean.SubDictionary;

public interface DictionaryMapper {
   public List<Dictionary>  list();
   
   public Dictionary getDictionaryById(int id);
   
   public int updateStatus(Dictionary dictionary);
   
   public List<SubDictionary> sublist(int dId);
   
   public int inputSub(SubDictionary subDictionary);
   
   public int input(Dictionary dictionary);
}
