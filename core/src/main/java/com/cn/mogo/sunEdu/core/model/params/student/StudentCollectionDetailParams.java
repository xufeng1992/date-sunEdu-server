package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;
import org.apache.commons.collections.CollectionUtils;

/**
 * Created by FE on 2016/7/11.
 */
public class StudentCollectionDetailParams extends BasicParams {

    private static final long serialVersionUID = -2218167080366539568L;

    private Integer id;

    private Integer studentId;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean verifyParams() {
        if (this.id == null || this.id <= 0) {
            this.setMsg(String.format("收藏编号有误 %s", id));
            return false;
        }
        if (studentId == null || studentId < 0) {
            this.setMsg(String.format("学生编号有误 %s", studentId));
            return false;
        }
        return true;
    }

}
