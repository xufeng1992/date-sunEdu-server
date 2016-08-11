package com.cn.mogo.sunEdu.core.service.Impl;/**
 * Created by Administrator on 2016/7/12 0012.
 */

import com.cn.mogo.sunEdu.core.dao.PushDoMapper;
import com.cn.mogo.sunEdu.core.model.PushDo;
import com.cn.mogo.sunEdu.core.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PushServiceImpl
 *
 * @author xufeng
 * @date 2016/7/12 0012
 */
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private PushDoMapper pushDoMapper;

    public List<PushDo> getPushInfo(int studentId) {
        return pushDoMapper.getPushInfo(studentId);
    }

    public int insertPushMessage(List<PushDo> pushDoList) {
        return pushDoMapper.insertPushMessage(pushDoList);
    }

}
