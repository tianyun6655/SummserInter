package com.ijianbao.framework.bean;

import java.util.Date;

public class Dictionary {
  private  int id;
  private  String name;
  private  int createBy;
  private   int status;
  private Date createTime;
  private int updateBy;
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
public int getCreateBy() {
	return createBy;
}
public void setCreateBy(int createBy) {
	this.createBy = createBy;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public int getUpdateBy() {
	return updateBy;
}
public void setUpdateBy(int updateBy) {
	this.updateBy = updateBy;
}
public Date getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}
  
}
