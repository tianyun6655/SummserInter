package com.ijianbao.model;

import java.io.Serializable;
import java.util.Date;

public class Patient implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7970072035602124677L;
	
	private int pid;
	private int cid;
	private String name;
	private int sex;
	private int age;
	private String cards;
	private String mobile;
	private int relation;
	private String contact;
	private String address;
	private String date;
	private int time;
	private int hid;
	private int office;
	private int isRegister;
	private int registerType;
	private String sympton;
	private int suid;
	private String ems;
	private String remark;
	private String backRemark;
	private int type;
	private int status;
	private Date createTime;
	private Date updateTime;
    private int productType;
	public int getProductType() {
		return productType;
	}
	public void setProductType(int productType) {
		this.productType = productType;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getRelation() {
		return relation;
	}
	public void setRelation(int relation) {
		this.relation = relation;
	}
	public String getSympton() {
		return sympton;
	}
	public void setSympton(String sympton) {
		this.sympton = sympton;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	public String getEms() {
		return ems;
	}
	public void setEms(String ems) {
		this.ems = ems;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBackRemark() {
		return backRemark;
	}
	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getCards() {
		return cards;
	}
	public void setCards(String cards) {
		this.cards = cards;
	}
	public int getOffice() {
		return office;
	}
	public void setOffice(int office) {
		this.office = office;
	}
	public int getIsRegister() {
		return isRegister;
	}
	public void setIsRegister(int isRegister) {
		this.isRegister = isRegister;
	}
	public int getRegisterType() {
		return registerType;
	}
	public void setRegisterType(int registerType) {
		this.registerType = registerType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Patient [pid=" + pid + ", cid=" + cid + ", name=" + name + ", sex=" + sex + ", age=" + age + ", cards="
				+ cards + ", mobile=" + mobile + ", relation=" + relation + ", contact=" + contact + ", address="
				+ address + ", date=" + date + ", time=" + time + ", hid=" + hid + ", office=" + office
				+ ", isRegister=" + isRegister + ", registerType=" + registerType + ", sympton=" + sympton + ", suid="
				+ suid + ", ems=" + ems + ", remark=" + remark + ", backRemark=" + backRemark + ", type=" + type
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", productType="
				+ productType + "]";
	}
	
}
