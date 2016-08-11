package com.cn.mogo.sunEdu.core.service.Impl;

/**
 * Created by Administrator on 2016/6/7 0007.
 */

import com.cn.mogo.sunEdu.core.common.PageableContent;
import com.cn.mogo.sunEdu.core.dao.*;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.model.params.*;
import com.cn.mogo.sunEdu.core.model.vo.*;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.result.BizResultCodeEnum;
import com.cn.mogo.sunEdu.core.service.TeacherService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UserServiceImpl
 *
 * @author xufeng
 * @date 2016/6/7 0007
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private HomeworkCollectMapper homeworkCollectMapper;
    @Autowired
    private AnswerSheetMapper answerSheetMapper;
    @Autowired
    private StudentMapper studentMapper;

    public Integer findExistUserByTelNO(String telNO) {
        // TODO Auto-generated method stub
        return teacherMapper.selectByMobile(telNO);
    }

    public int CreateUserSelective(Teacher teacher) {
        return teacherMapper.insertSelective(teacher);
    }

    public Teacher findUserByTelNo(String telNo) {
        return teacherMapper.findUserByTelNo(telNo);

    }

    public void updateUserByTelNo(Teacher teacher) {
        teacherMapper.updateByPrimaryKeyTeNO(teacher);

    }

    public Teacher findUserById(Integer teacherId) {
        return teacherMapper.findUserById(teacherId);
    }

    public List<StudentAdd> gruStuList(Teacher teacher) {

        Integer teacherId = teacher.getTeacherId();
        List<Group> teacherList = groupMapper.groupList(teacherId);
        List<StudentAdd> studentAddList = new ArrayList<StudentAdd>();
        for (Group te : teacherList) {
            Integer groupId = te.getGroupId();
            StudentAdd studentAdd = teacherMapper.selectByGroupId(groupId);
            if (studentAdd != null) {
                studentAddList.add(studentAdd);
            }
        }
        return studentAddList;
    }

    public int updateSubject(Teacher teacher) {
        return teacherMapper.updateSubject(teacher);
    }

    public PageableContent<List<StudentVo>> selectStudentAnswerSheetPages(StudentParams params) {
        List<StudentVo> resList = new ArrayList<StudentVo>();
        PageableContent<List<StudentVo>> pageList = new PageableContent<List<StudentVo>>(resList);
        int num = answerSheetMapper.selectStudentAnswerSheetCount(params);
        if (num > 0) {
            List<StudentVo> answerVoList = answerSheetMapper.selectStudentAnswerSheetPages(params);
            pageList.setContent(answerVoList);
            pageList.setCurrentPage(params.getCurrentPage());
            pageList.setRows(params.getRows());
            pageList.setTotalRows(num);
        }
        return pageList;
    }

    public PageableContent<List<HomeWorkStatusVo>> selectTeacherHomeWorkStatusList(HomeWorkParams params) {
        Date nowDate = new Date();
        List<HomeWorkStatusVo> unCheckList = new ArrayList<HomeWorkStatusVo>();
        List<HomeWorkStatusVo> checkList = new ArrayList<HomeWorkStatusVo>();
        List<HomeWorkStatusVo> resultList = new ArrayList<HomeWorkStatusVo>();
        PageableContent<List<HomeWorkStatusVo>> pageList = new PageableContent<List<HomeWorkStatusVo>>(resultList);
        //查询出老师发布的作业列表信息
        List<HomeWorkStatusVo> selectList = homeworkCollectMapper.selectTeacherHomeWorkStatusList(params);
        if (CollectionUtils.isNotEmpty(selectList)) {
            for (HomeWorkStatusVo parseVo : selectList) {
                //已批改作业答案数
                Integer checkedAnswerNum = parseVo.getCheckedAnswerNum() == null ? 0 : parseVo.getCheckedAnswerNum();
                //已经提交作业答案数
                Integer totalAnswerCommitNum = parseVo.getTotalAnswerCommitNum() == null ? 0 : parseVo.getTotalAnswerCommitNum();
                //提交学生数
                Integer commitStuNum = parseVo.getTotalCommitNum() == null ? 0 : parseVo.getTotalCommitNum();
                //需要完成该作业的学生数
                Integer needCommitStuNum = StringUtils.isBlank(parseVo.getStudentIds()) ? 0 : new ArrayList<String>(new HashSet<String>(Arrays.asList(parseVo.getStudentIds().split(",")))).size();
                Date deadline = parseVo.getDeadline();
                //获取已批作业集列表
                // 已批作业【截至时间之后的作业】
                // 1，没有学生交作业【该作业为已批作业】
                // 2, 学生交了作业的情况下【该作业上交的答案全部都要被老师批改之后才是已批阅】
                // 未批作业【截至时间之前的作业都是未批作业，截止时间之后如果上交答案有一个为批改就是待批阅】
                if (nowDate.getTime() > deadline.getTime()){
                    if( commitStuNum == 0 ){  // 情况，1
                        checkList.add(parseVo);
                    }else if( checkedAnswerNum.equals(totalAnswerCommitNum) ){ // 情况，2
                        checkList.add(parseVo);
                    }else{
                        unCheckList.add(parseVo);
                    }
                } else {
                    //截止时间未到情况下，只有当（上交学生数=该交学生数 && 已经批改答案数=已经提交答案数）的情况下才是已批改作业否则都是未批改作业
                    if( commitStuNum.equals(needCommitStuNum) && checkedAnswerNum.equals(totalAnswerCommitNum) && totalAnswerCommitNum > 0 ){
                        checkList.add(parseVo);
                    }else{
                        unCheckList.add(parseVo);
                    }
                }
            }
        }
        if (Integer.valueOf(1).equals(params.getSpecStatus())) { //已批作业
            resultList.addAll(checkList);
        } else {
            resultList.addAll(unCheckList);
        }
        pageList.setContent(resultList);
        pageList.setCurrentPage(params.getCurrentPage());
        pageList.setRows(params.getRows());
        pageList.setTotalRows(resultList == null ? 0 : resultList.size());
        return pageList;
    }

    public List<AnswerVo> selectAnswerSheetList(AnswerParams params) {
        List<AnswerVo> resList = answerSheetMapper.selectAnswerSheetList(params);
        return resList;
    }

    public BizResult<AnswerSheet> selectAnswerSheetDetails(Integer id) {
        AnswerSheet aSheet = answerSheetMapper.selectByPrimaryKey(id);
        if (aSheet == null) {
            return new BizResult<AnswerSheet>(BizResultCodeEnum.NOT_EXISTED);
        }
        return BizResult.success(aSheet);
    }

    public BizResult<List<StudentCommitVo>> selectStudentHomeWorkUnCommitList(StudentHomeWorkCommitParams commitParams) {
        List<StudentCommitVo> resList = new ArrayList<StudentCommitVo>();
        Integer workId = commitParams.getWorkId();
        HomeworkCollect hc = homeworkCollectMapper.selectByPrimaryKey(workId);
        if (hc == null || StringUtils.isBlank(hc.getStudentIds())) {
            return new BizResult<List<StudentCommitVo>>(BizResultCodeEnum.NOT_EXISTED);
        }
        //所有要完成改作业的学生
        List<String> totalStudentIds = new ArrayList<String>(new HashSet<String>(Arrays.asList(hc.getStudentIds().split(","))));
        List<String> commitStudentIds = homeworkCollectMapper.selectStudentHomeWorkList(workId);
        List<String> unCommitStudentIds = new ArrayList<String>();
        if (commitStudentIds == null || commitStudentIds.size() == 0) {
            unCommitStudentIds = totalStudentIds;
        } else {
            for (String stuId : totalStudentIds) {
                if (!commitStudentIds.contains(stuId)) {
                    unCommitStudentIds.add(stuId);
                }
            }
        }
        //获取未提交作业的学生信息
        if (unCommitStudentIds.size() > 0) {
            resList = studentMapper.selectStudentListByIds(unCommitStudentIds);
        }
        return BizResult.success(resList);
    }

    public BizResult<HomeworkStatisticsResultVo> selectHomeworkStatistics(HomeworkStatisticsParams statisticsParams) {
        List<HomeworkStatisticsVo> resultList = answerSheetMapper.selectHomeworkStatistics(statisticsParams);
        //处理数据
        if (CollectionUtils.isEmpty(resultList)) {
            return new BizResult<HomeworkStatisticsResultVo>(BizResultCodeEnum.NOT_EXISTED);
        }
        //学生总人数， 几个人数
        Integer totalStudentNum = 0, passStudentNum = 0;
        //最高得分 //最低总分 //作业总分数
        Double minScore = 0.0D, maxScore = 0.0D, workScore = 0.0D;
        Integer listSize = resultList.size();
        for (int i = 0; i < listSize; i++) {
            HomeworkStatisticsVo statisticsVo = resultList.get(i);
            //学生得分百分比
            Double scorePercent = statisticsVo.getScorePercent();
            if (scorePercent != null && scorePercent >= 60D) {
                passStudentNum = passStudentNum + 1;
            }
            //总人数获取
            if (StringUtils.isNotBlank(statisticsVo.getStudentIds()) && Integer.valueOf(0).equals(totalStudentNum)) {
                totalStudentNum = new ArrayList<String>(new HashSet<String>(Arrays.asList(statisticsVo.getStudentIds().split(",")))).size();
            }
            //学生最低总分
            if (i == 0) {
                minScore = statisticsVo.getStuScore();
            }
            //学生最高得分
            if (i == listSize - 1) {
                maxScore = statisticsVo.getStuScore();
                workScore = statisticsVo.getTotalScore();
            }
        }
        HomeworkStatisticsResultVo resultVo = new HomeworkStatisticsResultVo();
        resultVo.setMaxScore(maxScore);
        resultVo.setMinScore(minScore);
        resultVo.setWorkScore(workScore);
        resultVo.setPassStudentNum(passStudentNum);
        resultVo.setTotalStudentNum(totalStudentNum);
        return BizResult.success(resultVo);
    }
}

