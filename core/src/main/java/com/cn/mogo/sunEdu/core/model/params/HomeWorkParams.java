package com.cn.mogo.sunEdu.core.model.params;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import com.cn.mogo.sunEdu.core.common.Page;

/**
 * Created by FE on 2016/6/21.
 */
public class HomeWorkParams extends Page implements Serializable {

    private String token;
    private Integer teacherId;
    private Integer specStatus;
    private String deadline;
    private String msg ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSpecStatus() {
        return specStatus;
    }

    public void setSpecStatus(Integer specStatus) {
        this.specStatus = specStatus;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getMsg() {
        return msg;
    }

    public boolean verifyParams(){
        if(StringUtils.isNotBlank(deadline)){
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try{
                formatter.parse(deadline);
                return true;
            }catch(Exception e){
                msg = "日期格式有误,正确格式[yyyy-MM-dd HH:mm:ss]";
                return false;
            }
        }
        return true;
    }
}
