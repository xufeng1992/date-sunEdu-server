package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.HisHomeworkCollBean;
import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.HomeWorkParams;
import com.cn.mogo.sunEdu.core.model.params.student.ScoreReportParam;
import com.cn.mogo.sunEdu.core.model.params.student.StudentHomeWorkStatusParams;
import com.cn.mogo.sunEdu.core.model.vo.HomeWorkStatusVo;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentHomeWorkStatusVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkCollectMapper {

    List<HisHomeworkCollBean> hisHomeworkCollList(Integer groupId);
    //未上传答案列表
    List<HomeworkCollect> yetUpload(Integer teacherId);
    //发布作业
    Integer publishHomeWork(HomeworkCollect hwc);
    //作业待发布列表
    List<HomeworkCollect> getAnnounced(Teacher teacher);
    //立刻发布作业列表
    Integer immePublish(HomeworkCollect homeworkCollect);
    //删除待发布作业
    Integer delPublish(HomeworkCollect homeworkCollect);
    //根据主键查询作业集信息
    HomeworkCollect selectByPrimaryKey(Integer workId);

    //作业集已经提交的学生编号列表
    List<String> selectStudentHomeWorkList(Integer workId);

    //查询出全部作业列表
    List<HomeWorkStatusVo> selectTeacherHomeWorkStatusList(HomeWorkParams params);

    /**
     * 学生端查询作业列表显示
     * 查询学生作业列表显示【学生待批，已批作业】
     * @return
     */
    List<StudentHomeWorkStatusVo> selectStudentHomeWorkStatusList(StudentHomeWorkStatusParams params);
    //作业集IdList
    List<Integer> selectHomeworkIdList(Integer studentId);
    //学生作业未发布答案列表
    List<HomeworkCollect> selectYetUpload(String hwIdList);
    //学生作业报表
    List<HomeworkCollect> getHWList(ScoreReportParam scoreReportParam);
}