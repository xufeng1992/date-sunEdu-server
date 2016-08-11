package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.Group;
import com.cn.mogo.sunEdu.core.model.HisHomeworkCollBean;
import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.student.ScoreReportParam;
import com.cn.mogo.sunEdu.core.model.vo.student.ScoreReportVo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface HomeworkCollectService {

    //历史作业列表
    List<HisHomeworkCollBean> getHisHomeworkCollList(Group group);
    //未上传答案
    List<HomeworkCollect> yetUpload(Teacher teacher);
    //发布作业
    Integer publishHomeWork(HomeworkCollect hwc);
    //待发布列表
    List<HomeworkCollect> getAnnounced(Teacher teacher);
    //立刻发布作业
    Integer immePublish(HomeworkCollect homeworkCollect);
    //删除待发布作业
    int delPublish(HomeworkCollect homeworkCollect);
    //学生作业报表
    ScoreReportVo scoreReport(ScoreReportParam scoreReportParam);
}
