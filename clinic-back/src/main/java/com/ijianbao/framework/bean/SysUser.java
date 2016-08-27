package com.ijianbao.framework.bean;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4955796767942270733L;
	
	private int id;
    private String name;
    private String password;
    private String mobile;
    private int age;
    private int isService;
    private int status;
    private int createUser;
    private Date createTime;
    private int updateUser;
    private Date updateTime;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getIsService() {
		return isService;
	}
	public void setIsService(int isService) {
		this.isService = isService;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(int updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "SysUser [id=" + id + ", name=" + name + ", password=" + password + ", mobile=" + mobile + ", age=" + age
				+ ", isService=" + isService + ", status=" + status + ", createUser=" + createUser + ", createTime="
				+ createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}
    
}
