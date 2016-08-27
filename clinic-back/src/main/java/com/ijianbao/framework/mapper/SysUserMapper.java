package com.ijianbao.framework.mapper;

import java.util.List;

import com.ijianbao.framework.bean.SysUser;



public interface SysUserMapper {
	
    public SysUser findSysUser(SysUser sysUser);
    
    public int saveSysUser(SysUser sysUser);
    
    public List<SysUser> list();
    
    public SysUser getSysUser(int id);
    
    public int updateSysUser(SysUser sysUser);
    
    public List<SysUser> getServices();
    
    public SysUser getUserByMobile(SysUser sysUser);
    
    public SysUser getUserByMobile2(String mobile);
    
}
