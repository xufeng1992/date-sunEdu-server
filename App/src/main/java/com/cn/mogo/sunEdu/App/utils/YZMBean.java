package com.cn.mogo.sunEdu.App.utils;

import java.io.Serializable;

public class YZMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1168270148992209444L;
	
	//手机号码
	private String telNumber;
	private String code;
	//失效时间
	private Integer timeout;
	//类型
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	

}
