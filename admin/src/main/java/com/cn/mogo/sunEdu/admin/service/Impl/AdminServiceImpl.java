package com.cn.mogo.sunEdu.admin.service.Impl;

import com.cn.mogo.sunEdu.admin.dao.AdminDao;
import com.cn.mogo.sunEdu.admin.model.Admin;
import com.cn.mogo.sunEdu.admin.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chang on 2016/6/3.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    public PageInfo<Admin> findAll(int pageSize, int currentPage){
        return new PageInfo<Admin>(adminDao.findAll(),6);
    }
}
