package com.cn.mogo.sunEdu.admin.controller;

import com.cn.mogo.sunEdu.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by chang on 2016/6/3.
 */
@RequestMapping("user")
@Controller
public class UserController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        adminService.findAll(6,1);
        return "user/list";
    }
}
