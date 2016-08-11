package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.PushDo;

import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public interface PushService {
    //获取推送信息
    List<PushDo> getPushInfo(int studentId);
    //保存推送消息
    int insertPushMessage(List<PushDo> pushDoList);

}
