package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.PushDo;
import com.cn.mogo.sunEdu.core.model.PushDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PushDoMapper {
//    int deleteByExample(PushDoExample example);
//
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(PushDo record);
//
//    int insertSelective(PushDo record);
//
//    List<PushDo> selectByExample(PushDoExample example);
//
//    PushDo selectByPrimaryKey(Integer id);
//
//    int updateByExampleSelective(@Param("record") PushDo record, @Param("example") PushDoExample example);
//
//    int updateByExample(@Param("record") PushDo record, @Param("example") PushDoExample example);
//
//    int updateByPrimaryKeySelective(PushDo record);
//
//    int updateByPrimaryKey(PushDo record);

    List<PushDo> getPushInfo(int studentId);
    //推送消息保存在数据库
    int insertPushMessage(List<PushDo> pushDoList);
}