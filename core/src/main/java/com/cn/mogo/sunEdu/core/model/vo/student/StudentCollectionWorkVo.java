package com.cn.mogo.sunEdu.core.model.vo.student;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author FE
 *         学生收藏的作业 返回数据对象
 */
public class StudentCollectionWorkVo implements Serializable {

    private static final long serialVersionUID = 8030698389617695513L;

    private Integer id;  //主键

    private String homeworkName; //作业名称

    private Integer collectionId;  //收藏对应编号

    private Date collectionTime;//收藏时间

    private Integer collectionTypes; //收藏类型

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionTypes() {
        return collectionTypes;
    }

    public void setCollectionTypes(Integer collectionTypes) {
        this.collectionTypes = collectionTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollectionTime() {
        if (this.collectionTime == null) {
            return "1970-01-01";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(collectionTime);
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }
}
