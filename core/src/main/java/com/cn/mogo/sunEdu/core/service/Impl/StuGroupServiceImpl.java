package com.cn.mogo.sunEdu.core.service.Impl;/**
 * Created by Administrator on 2016/6/14 0014.
 */

import com.cn.mogo.sunEdu.core.common.Push;
import com.cn.mogo.sunEdu.core.dao.GroupMapper;
import com.cn.mogo.sunEdu.core.dao.PushDoMapper;
import com.cn.mogo.sunEdu.core.dao.StuGroupMapper;
import com.cn.mogo.sunEdu.core.dao.StudentMapper;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.model.params.GroupBean;
import com.cn.mogo.sunEdu.core.model.params.StuForGroup;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;
import com.cn.mogo.sunEdu.core.model.vo.PushInfoVo;
import com.cn.mogo.sunEdu.core.service.StuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * StuGroupServiceImpl
 *
 * @author xufeng
 * @date 2016/6/14 0014
 */
@Service
public class StuGroupServiceImpl implements StuGroupService {

    @Autowired
    private StuGroupMapper stuGroupMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private PushDoMapper pushDoMapper;

    public int getCount(Integer groupId) {
        return stuGroupMapper.getCount(groupId);
    }

    public List<Student> getStuList(Integer groupId) {

        List<Integer> stuIdList = new ArrayList<Integer>();
        List<Student> studentList = new ArrayList<Student>();

        stuIdList = stuGroupMapper.getStuIdList(groupId);
        for(Integer stuId : stuIdList) {
            Student student = new Student();
            student = studentMapper.selectByStuId(stuId);
            studentList.add(student);
        }
        return studentList;
    }

    public int delStudent(StuGroup stuGroup) {
        Integer groupId = stuGroup.getGroupId();
        Integer studentId = stuGroup.getStudentId();
        int ret = 0;
        if(groupId>0 && studentId>0){
            byte s = 2;
            stuGroup.setStatus(s);
            ret = stuGroupMapper.updateStudent(stuGroup);
        } else {
            ret = 0;
        }
        return ret;
    }

    public List<Combine> getMyClassMsg(Integer teacherId) {

        List<Combine> combineList = stuGroupMapper.getMyClassMsg(teacherId);
        return combineList;
    }

    //确认添加
    public int relAdd(StuForGroup stuForGroup) {

        int result;
        //班群id
        Integer groupId = stuForGroup.getGroupId();
        //学生ids
        Integer[] studentds = stuForGroup.getStudentId();
        //状态
        byte status = stuForGroup.getStatus();
        //数组转list
        List<Integer> studentIdList = new ArrayList<Integer>();
        Collections.addAll(studentIdList,studentds);
        //添加学生List
        List<StuGroup> stuGroupList = new ArrayList<StuGroup>();
        //更新学生List
        List<StuGroup> stuUpGroupList = new ArrayList<StuGroup>();
        List<Integer> valList = new ArrayList<Integer>();
        Collections.addAll(valList,studentds);
        //根据班群id查询学生id
        List<Integer> getStuIdList = stuGroupMapper.getStuIdList(groupId);
        List<Integer> getUpStuIdList = stuGroupMapper.getUpStuIdList(groupId);
        //两个list去重
        studentIdList.removeAll(getStuIdList);
        //遍历学生Id
        for(Integer studentId:studentIdList) {
            StuGroup stuGroup = new StuGroup();
            stuGroup.setGroupId(groupId);
            stuGroup.setStudentId(studentId);
            stuGroup.setStatus(status);
            stuGroupList.add(stuGroup);
        }
        //两个list取交集
        valList.retainAll(getUpStuIdList);

        if ((stuGroupList.size())<1 && (valList.size())<1 ){
            result = 0;
        } else {
            int rec1=0;
            int rec2 =0;
            if (stuGroupList.size()>0){
               rec1 =  stuGroupMapper.relAdd(stuGroupList);
            } else if(valList.size()>0) {
                //已经申请过加群的同学进行更新状态
                Map<String,Object> params = new HashMap<String, Object>();
                params.put("valList",valList);
                params.put("groupId",groupId);
                rec2 =  stuGroupMapper.relAddUpdate(params);
            }
            result = rec1+rec2;
            //获取老师名和班级名
            PushInfoVo pushInfoVo = groupMapper.getPushInfo(groupId);
            //推送消息
            String pushMessage = "你已经被"+(pushInfoVo.getName()) + "老师拉进" + pushInfoVo.getGroupName()+"作业班群";
            //获取学生手机号码
            studentIdList.addAll(valList);
            List<String> stuTelList = studentMapper.selectTelByIds(studentIdList);

            List<PushDo> pushDoList = new ArrayList<PushDo>();
            for(int studentId:studentIdList) {
                PushDo pushDo = new PushDo();
                pushDo.setPushContent(pushMessage);
                pushDo.setPushPeople(pushInfoVo.getTeacherId());
                pushDo.setReceiver(studentId);
                pushDoList.add(pushDo);
            }
            //推送消息保存在数据库
            int ret = pushDoMapper.insertPushMessage(pushDoList);
            //推送
            Push.sendPush(pushMessage,stuTelList);
        }
        return result;
    }

    //请求审核
    public int checkApply(Combine combine) {
        int ret=0;
        if((combine.getGroupId()) > 0 && (combine.getStudentId())>0 && (combine.getStatus()) > 0) {
            ret = stuGroupMapper.checkApply(combine);
            Student student = studentMapper.selectByStuId(combine.getStudentId());
            List<String> telNoList = new ArrayList<String>();
            telNoList.add(student.getTelNo());
            String pushMessage="";
            if((combine.getStatus())==1) {
                //推送消息 通过
                 pushMessage = "你已成功加入"+(combine.getGroupName())+"群";
            } else {
                //推送消息 拒绝
                 pushMessage = "你申请加入"+(combine.getGroupName())+"群被拒绝";
            }
            //查询老师信息
            Group group = groupMapper.getGroupDetails(combine.getGroupId());
            List<PushDo> pushDoList = new ArrayList<PushDo>();
            PushDo pushDo = new PushDo();
            pushDo.setReceiver(student.getStudentId());
            pushDo.setPushContent(pushMessage);
            pushDo.setPushPeople(group.getCreatePeople());
            pushDoList.add(pushDo);
            //推送消息保存在数据库
            int result= pushDoMapper.insertPushMessage(pushDoList);
            Push push = new Push();
            push.sendPush(pushMessage,telNoList);
        } else {
            ret = 0;
        }
        return ret;
    }

    public List<Combine> getGroupClassMsg(Integer groupId) {

        return stuGroupMapper.getGroupClassMsg(groupId);
    }
    //学生端班群列表
    public List<GroupBean> getGroupBeanList(StudentParam studentParam) {

        Integer studentId = studentParam.getStudentId();
        List<GroupBean> groupBeanList = new ArrayList<GroupBean>();
        List<Group> groupList = stuGroupMapper.getGroupList(studentId);
        for(Group group : groupList) {
            GroupBean groupBean = new GroupBean();
            //群Id
            groupBean.setGroupId(group.getGroupId());
            //老师Id
            groupBean.setCreatePeople(group.getCreatePeople());
            //群简介
            groupBean.setAnnouncement(group.getAnnouncement());
            //班群人数
            Integer count = stuGroupMapper.getCount(group.getGroupId());
            groupBean.setCountPeople(count);
            //班群名
            groupBean.setGroupName(group.getGroupName());
            groupBeanList.add(groupBean);
        }
        return groupBeanList;
    }
    //学生申请添加群
    public Integer applyAdd(StuGroup stuGroup) {
        Integer result;
        //判断是否已经申请
        Integer ret = stuGroupMapper.selectByStuGroup(stuGroup);
        if (ret>0) {
            result = -1;
        } else {
         //学生申请添加群
         result = stuGroupMapper.stuApplyAdd(stuGroup);
        }
        return result;
    }
    //班群成员
    public List<Student> getAddStuIdList(Integer groupId) {
        List<Integer> stuIdList = new ArrayList<Integer>();
        List<Student> studentList = new ArrayList<Student>();

        stuIdList = stuGroupMapper.getAddStuIdList(groupId);
        for(Integer stuId : stuIdList) {
            Student student = new Student();
            student = studentMapper.selectByStuId(stuId);
            studentList.add(student);
        }
        return studentList;
    }

}
