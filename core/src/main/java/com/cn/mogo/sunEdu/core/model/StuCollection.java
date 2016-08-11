package com.cn.mogo.sunEdu.core.model;

import java.io.Serializable;
import java.util.Date;

public class StuCollection implements Serializable {
    private Integer id;

    private Integer studentId;

    private Integer collectionTypes;

    private Integer collectionId;

    private String subCollectionIds;

    private Date collectionTime;

    private Integer collectionStatus;

    private static final long serialVersionUID = -8571401740361773914L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCollectionTypes() {
        return collectionTypes;
    }

    public void setCollectionTypes(Integer collectionTypes) {
        this.collectionTypes = collectionTypes;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getSubCollectionIds() {
        return subCollectionIds;
    }

    public void setSubCollectionIds(String subCollectionIds) {
        this.subCollectionIds = subCollectionIds == null ? null : subCollectionIds.trim();
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
}