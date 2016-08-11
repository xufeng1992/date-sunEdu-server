package com.cn.mogo.sunEdu.core.model;

import java.io.Serializable;
import java.util.Date;

public class PushDo implements Serializable {
    private Integer id;

    private String pushContent;

    private Date pushTime;

    private Integer pushDevice;

    private Integer pushStatus;

    private Integer senderAccountType;

    private Integer pushPeople;

    private Integer receiver;

    private static final long serialVersionUID = -6689305777754190359L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent == null ? null : pushContent.trim();
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    public Integer getPushDevice() {
        return pushDevice;
    }

    public void setPushDevice(Integer pushDevice) {
        this.pushDevice = pushDevice;
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Integer getSenderAccountType() {
        return senderAccountType;
    }

    public void setSenderAccountType(Integer senderAccountType) {
        this.senderAccountType = senderAccountType;
    }

    public Integer getPushPeople() {
        return pushPeople;
    }

    public void setPushPeople(Integer pushPeople) {
        this.pushPeople = pushPeople;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }
}