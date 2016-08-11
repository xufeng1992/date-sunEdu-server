package com.cn.mogo.sunEdu.admin.dao;

import com.cn.mogo.sunEdu.admin.model.Admin;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chang on 2016/6/3.
 */
@Repository
public interface AdminDao {

    @Select("select * from cms_admin")
    @ResultMap("BaseResultMap")
    List<Admin> findAll();
}
