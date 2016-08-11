package com.cn.mogo.sunEdu.core.model.params;


/**
 * Created by FE on 2016/7/1.
 */
public class HomeworkStatisticsParams extends BasicParams {


    private String msg;
    private Integer workId;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getMsg() {
        return msg;
    }

    public boolean verifyParams(){
        if( workId == null || workId <= 0){
            msg = String.format("作业集编号有误, workId = %s" ,workId);
            return false ;
        }
        return true ;
    }
}
