package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.common.PageableContent;
import com.cn.mogo.sunEdu.core.model.AnswerSheet;
import com.cn.mogo.sunEdu.core.model.StudentAdd;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.*;
import com.cn.mogo.sunEdu.core.model.vo.*;
import com.cn.mogo.sunEdu.core.result.BizResult;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public interface TeacherService {
    //查询
    Integer findExistUserByTelNO(String telNO);
    //注册
    int CreateUserSelective(Teacher teacher);

    Teacher findUserByTelNo(String telNo);

    void  updateUserByTelNo(Teacher teacher);
    // 根据ID查询
    Teacher findUserById(Integer teacherId);

    List<StudentAdd> gruStuList(Teacher teacher);

    //更新科目
    int updateSubject(Teacher teacher);

    //老师作业列表【待批改，已批阅】
    PageableContent<List<HomeWorkStatusVo>> selectTeacherHomeWorkStatusList(HomeWorkParams params);

    //作业集的学生作业列表信息
    PageableContent<List<StudentVo>> selectStudentAnswerSheetPages(StudentParams params);

    //学生作业集答案列表
    List<AnswerVo> selectAnswerSheetList(AnswerParams params);

    /**
     * 根据答案主键查询答案详细信息
     * @param id
     * @return
     */
    BizResult<AnswerSheet> selectAnswerSheetDetails(Integer id);


    /**
     *未提交作业学生名单接口
     */
    BizResult<List<StudentCommitVo>> selectStudentHomeWorkUnCommitList(StudentHomeWorkCommitParams commitParams);

    /**
     * 作业集统计信息
     */
    BizResult<HomeworkStatisticsResultVo> selectHomeworkStatistics(HomeworkStatisticsParams statisticsParams);
}
