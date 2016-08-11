package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;

/**
 * Created by FE on 2016/7/6.
 */
public class HomeworkDetailParams extends BasicParams {

    private Integer workId;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public boolean verifyParams(){
        if( workId == null || workId <= 0){
            this.setMsg(String.format("作业集编号有误, workId = %s" ,workId));
            return false ;
        }
        return true ;
    }
}
