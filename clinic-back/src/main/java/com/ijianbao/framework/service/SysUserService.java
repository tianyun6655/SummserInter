package com.ijianbao.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijianbao.framework.bean.SysUser;
import com.ijianbao.framework.mapper.SysUserMapper;




@Service
@Transactional
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser findSysUser(SysUser sysUser){
        return sysUserMapper.findSysUser(sysUser);
    }
    
    public int saveSysUser(SysUser sysUser)
    {
    	SysUser user = sysUserMapper.getUserByMobile(sysUser);
    	if(user==null){
        	return sysUserMapper.saveSysUser(sysUser);
    	}
    	return 0;
    }
    
    public List<SysUser> list() {
		return sysUserMapper.list();
	}
    
    public SysUser getSysUser(int id){
    	return sysUserMapper.getSysUser(id);
    }
    
    public int updateSysUser(SysUser sysUser){
    	return sysUserMapper.updateSysUser(sysUser);
    }
    
    public List<SysUser> getServices(){
    	return sysUserMapper.getServices();
    }

   public SysUser getUserByMobile2(String mobile){
	   return sysUserMapper.getUserByMobile2(mobile);
	   
   }
   

}
