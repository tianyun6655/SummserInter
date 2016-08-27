package com.ijianbao.framework.bean;

import java.io.Serializable;
import java.util.Date;

public class Code implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5247469131212325896L;
	
	private int cid;
	private String mobile;
	private String openid;
	private String code;
	private Date createTime;
	private Date invalidTime;
	private int type;
	private int status;
	private String message;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Code [cid=" + cid + ", mobile=" + mobile + ", openid=" + openid + ", code=" + code + ", createTime="
				+ createTime + ", invalidTime=" + invalidTime + ", type=" + type + ", status=" + status + ", message="
				+ message + "]";
	}
	
}
