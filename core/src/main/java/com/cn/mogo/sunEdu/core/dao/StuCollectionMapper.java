package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.StuCollection;
import com.cn.mogo.sunEdu.core.model.StuCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuCollectionMapper {
    int deleteByExample(StuCollectionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StuCollection record);

    int insertSelective(StuCollection record);

    List<StuCollection> selectByExample(StuCollectionExample example);

    StuCollection selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StuCollection record, @Param("example") StuCollectionExample example);

    int updateByExample(@Param("record") StuCollection record, @Param("example") StuCollectionExample example);

    int updateByPrimaryKeySelective(StuCollection record);

    int updateByPrimaryKey(StuCollection record);
}