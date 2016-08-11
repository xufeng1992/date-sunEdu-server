package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by FE on 2016/7/11.
 * 学生端收藏作业或者题目接口操作
 */
public class StudentCollectionInsertParams extends BasicParams {

    private static final long serialVersionUID = -2218167080366539568L;

    private Integer workId;

    private Integer studentId;

    private Set<String> subCollectionIds;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Set<String> getSubCollectionIds() {
        if (CollectionUtils.isNotEmpty(subCollectionIds)) {
            for (String s : subCollectionIds) {
                if (StringUtils.isBlank(s)) {
                    subCollectionIds.remove(s);
                }
            }
        }
        return subCollectionIds;
    }

    public void setSubCollectionIds(Set<String> subCollectionIds) {
        this.subCollectionIds = subCollectionIds;
    }

    public boolean verifyParams() {
        if (this.workId == null || this.workId <= 0) {
            this.setMsg(String.format("作业编号有误 %s", workId));
            return false;
        }
        if (studentId == null || studentId < 0) {
            this.setMsg(String.format("学生编号有误 %s", studentId));
            return false;
        }
        if (CollectionUtils.isEmpty(this.subCollectionIds)) {
            this.setMsg(String.format("收藏的编号不能为空 %s", subCollectionIds));
            return false;
        }
        return true;
    }

}
