package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by FE on 2016/7/11.
 * 取消收藏入参对象
 */
public class StudentCollectionUpdateParams extends BasicParams {

    private static final long serialVersionUID = -2218167080366539568L;

    private Integer workId; //收藏的编号

    private Integer studentId;

    private Set<String> unSubCollectionIds;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Set<String> getUnSubCollectionIds() {
        if (CollectionUtils.isNotEmpty(unSubCollectionIds)) {
            for (String s : unSubCollectionIds) {
                if (StringUtils.isBlank(s)) {
                    unSubCollectionIds.remove(s);
                }
            }
        }
        return unSubCollectionIds;
    }

    public void setUnSubCollectionIds(Set<String> unSubCollectionIds) {
        this.unSubCollectionIds = unSubCollectionIds;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean verifyParams() {
        if (this.workId == null || this.workId <= 0) {
            this.setMsg(String.format("取消收藏的作业编号有误 %s", workId));
            return false;
        }
        if (studentId == null || studentId < 0) {
            this.setMsg(String.format("学生编号有误 %s", studentId));
            return false;
        }
        if (CollectionUtils.isEmpty(this.unSubCollectionIds)) {
            this.setMsg(String.format("收藏的题目编号不能为空 %s", unSubCollectionIds));
            return false;
        }
        return true;
    }
}
