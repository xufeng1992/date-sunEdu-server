package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.AnswerSheet;
import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.params.*;
import com.cn.mogo.sunEdu.core.model.params.student.AnswerCheckDetailParams;
import com.cn.mogo.sunEdu.core.model.params.student.UpdateAnswerParams;
import com.cn.mogo.sunEdu.core.model.vo.*;
import com.cn.mogo.sunEdu.core.model.vo.student.AnswerCheckDetailResultVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnswerSheet record);

    int insertSelective(AnswerSheet record);

    AnswerSheet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnswerSheet record);

    int updateByPrimaryKey(AnswerSheet record);

    /**
     *  <!--查询作业集下 已经批阅 未批阅的学生列表信息-->
     * @param params
     * @return
     */
    List<StudentVo> selectStudentAnswerSheetPages(StudentParams params);

    /**
     *  <!--查询作业集下 已经批阅 未批阅的学生列表信息--> 统计条数
     * @param params
     * @return
     */
    int selectStudentAnswerSheetCount(StudentParams params);

    //学生作业集答案列表
    List<AnswerVo> selectAnswerSheetList(AnswerParams params);

    /**
     * 老师批改作业是修改学生作业接口方法
     * @param sheetParams
     * @return
     */
    int updateDetailByPrimaryKeySelective(AnswerSheetParams sheetParams);

    /**
     * 已批改作业提交时间列表
     * @param checkParams
     * @return
     */
    List<AnswerCheckVo> selectCheckedAnswerCommitList(AnswerCheckParams checkParams );

    /**
     * 已批改作业 正确率列表
     * @param accuracyParams
     * @return
     */
    List<AnswerAccuracyVo> selectCheckedAnswerAccuracyList(AnswerAccuracyParams accuracyParams );

    /**
     * 作业集统计信息
     */
    List<HomeworkStatisticsVo> selectHomeworkStatistics(HomeworkStatisticsParams statisticsParams);

    /**
     * 学生提交作业答案
     * @return
     */
    int insertAnswerSheetBatch(@Param("list")List<UpdateAnswerParams> insertList);

    /**
     * 学生修改作业答案
     * @return
     */
    int updateAnswerSheetBatch(@Param("list") List<UpdateAnswerParams> updateList);
    
    //答题表中作业集IdList
    List<Integer> selectHomeworkIdList(Integer studentId);
    /**
     * 答案详细信息
     * @param detailParams
     * @return
     */
    AnswerCheckDetailResultVo selectStudentAnswerDetailById(AnswerCheckDetailParams detailParams);
    //获取作业得分
    double getScoreSum(AnswerSheet answerSheet);

    //根据编号查询
    List<AnswerSheet> selectAnswerSheetListByIds(List<Integer> ids);
}