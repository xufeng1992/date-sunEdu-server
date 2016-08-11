package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.params.AnswerParams;
import com.cn.mogo.sunEdu.core.model.params.student.*;
import com.cn.mogo.sunEdu.core.model.vo.student.*;
import com.cn.mogo.sunEdu.core.result.BizResult;

import java.util.List;

/**
 * Created by FE on 2016/7/5.
 * 学生作业操作接口
 */
public interface StudentHomeWorkService {

    /**
     * 查询学生作业列表显示【学生待批，已批作业】
     *
     * @return
     */
    BizResult<List<StudentHomeWorkStatusVo>> selectStudentHomeWorkList(StudentHomeWorkStatusParams params);

    /**
     * 查询学生作查询作业相信信息
     *
     * @return
     */
    BizResult<HomeworkDetailVo> selectStudentHomeWorkDetail(HomeworkDetailParams params);

    /**
     * 查询学生作业答案列表显示
     *
     * @param params
     * @return
     */
    BizResult<AnswerUpdateVo> selectStudentHomeWorkAnswerList(AnswerParams params);


    /**
     * 学生发布答案
     */
    BizResult<String> insertStudentHomeWorkAnswer(List<UpdateAnswerParams> insertList);

    /**
     * 学生修改答案
     *
     * @param updateList
     * @return
     */
    BizResult<String> updateStudentHomeWorkAnswer(List<UpdateAnswerParams> updateList);

    /**
     * 学生未发布答案列表
     * @param studentId
     * @return
     */
    List<HomeworkCollect> selectYetUpload(Integer studentId);

    /**
     * 学生查看老师发布作业的答案
     *
     * @param lookupParams
     * @return
     */
    BizResult<HomeWorkAnswerLookupVo> selectHomeWorkAnswerLookup(HomeWorkAnswerLookupParams lookupParams);


    /**
     * 查询某个作业集答案列表信息
     * @param lookupParams
     * @return
     *
     */
    BizResult<List<AnswerCheckListResultVo>> selectHomeWorkAnswerCheckedList(HomeWorkAnswerLookupParams lookupParams);

    /**
     * 查询答案相信信息
     * @param detailParams
     * @return
     */
    BizResult<AnswerCheckDetailResultVo> selectHomeWorkAnswerCheckedDetail(AnswerCheckDetailParams detailParams);
}
