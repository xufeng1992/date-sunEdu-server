package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.Homework;
import com.cn.mogo.sunEdu.core.model.params.HomeWorkParams;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKeyWithBLOBs(Homework record);

    int updateByPrimaryKey(Homework record);

    List<Homework> selectHomeworkList(HomeWorkParams workParams);

}