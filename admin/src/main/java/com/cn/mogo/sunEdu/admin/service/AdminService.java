package com.cn.mogo.sunEdu.admin.service;

import com.cn.mogo.sunEdu.admin.model.Admin;
import com.github.pagehelper.PageInfo;

/**
 * Created by chang on 2016/6/3.
 */
public interface AdminService {
    PageInfo<Admin> findAll(int pageSize, int currentPage);
}
