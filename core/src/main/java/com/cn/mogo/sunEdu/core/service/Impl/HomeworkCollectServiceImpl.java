package com.cn.mogo.sunEdu.core.service.Impl;/**
 * Created by Administrator on 2016/6/16 0016.
 */

import com.cn.mogo.sunEdu.core.dao.AnswerSheetMapper;
import com.cn.mogo.sunEdu.core.dao.HomeworkCollectMapper;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.model.params.student.ScoreReportParam;
import com.cn.mogo.sunEdu.core.model.vo.student.ScoreReportVo;
import com.cn.mogo.sunEdu.core.service.AnswersSheetService;
import com.cn.mogo.sunEdu.core.service.HomeworkCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * HomeworkCollectServiceImpl
 *
 * @author xufeng
 * @date 2016/6/16 0016
 */
@Service
public class HomeworkCollectServiceImpl implements HomeworkCollectService {

    @Autowired
    private HomeworkCollectMapper homeworkCollectMapper;
    @Autowired
    private AnswerSheetMapper answerSheetMapper;

    public List<HisHomeworkCollBean> getHisHomeworkCollList(Group group) {

        //群Id
        Integer groupId = group.getGroupId();
        List<HisHomeworkCollBean> hisHomeworkCollBeanList = homeworkCollectMapper.hisHomeworkCollList(groupId);

        List<HisHomeworkCollBean> result = new ArrayList<HisHomeworkCollBean>();

        if(hisHomeworkCollBeanList!=null || !hisHomeworkCollBeanList.equals("")) {

            for (HisHomeworkCollBean hisHomeworkColl : hisHomeworkCollBeanList) {
                Date deadline = hisHomeworkColl.getDeadline();
                int validDay = computeDate(deadline,7);
                if(validDay>0) {
                    int finishNum =  hisHomeworkColl.getAnswerSheetList().size();
                    hisHomeworkColl.setFinishNum(finishNum);
                    String studentIds = hisHomeworkColl.getStudentIds();
                    String[] strArray=null;
                    strArray = convertStrToArray(studentIds);
                    //学生总人数
                    hisHomeworkColl.setStudentNum(strArray.length);
                    //总题数
                    int totalNumber = hisHomeworkColl.getTotalNumber();
                    //总题数
                    hisHomeworkColl.setTotalNumber(totalNumber);
                    //保留天数
                    hisHomeworkColl.setValidDay(validDay);

                    result.add(hisHomeworkColl);
                }

            }

        }
        return result;
    }

    public List<HomeworkCollect> yetUpload(Teacher teacher) {
        List<HomeworkCollect> homeworkCollectList = homeworkCollectMapper.yetUpload(teacher.getTeacherId());

        return homeworkCollectList;
    }

    //发布作业
    public Integer publishHomeWork(HomeworkCollect hwc) {

        return homeworkCollectMapper.publishHomeWork(hwc);
    }

    //待发布列表
    public List<HomeworkCollect> getAnnounced(Teacher teacher) {

        return homeworkCollectMapper.getAnnounced(teacher);
    }

    //立刻发布作业
    public Integer immePublish(HomeworkCollect homeworkCollect) {
        return homeworkCollectMapper.immePublish(homeworkCollect);
    }

    //删除待发布作业
    public int delPublish(HomeworkCollect homeworkCollect) {
        return homeworkCollectMapper.delPublish(homeworkCollect);
    }

    //学生作业报表
    public ScoreReportVo scoreReport(ScoreReportParam scoreReportParam) {

        List<HomeworkCollect> homeworkCollectList = homeworkCollectMapper.getHWList(scoreReportParam);
        ScoreReportVo scoreReportVo = new ScoreReportVo();
        List<ScoreReportParam> scoreReportParamList= new ArrayList<ScoreReportParam>();
        if(homeworkCollectList!=null && homeworkCollectList.size()>0) {
            double accuracySum =0.0;
            for (HomeworkCollect homeworkCollect:homeworkCollectList) {

                ScoreReportParam scoreReport = new ScoreReportParam();
                //做业集Id
                Integer id = homeworkCollect.getId();
                AnswerSheet answerSheet = new AnswerSheet();
                answerSheet.setStudentId(scoreReportParam.getStudentId());
                answerSheet.setSubCollect(homeworkCollect.getId());
                //获取该作业得分
                double answerScore = answerSheetMapper.getScoreSum(answerSheet);
                //正确率
                double accuracy = (answerScore/(homeworkCollect.getTotalPoints()))*100;
                accuracySum = accuracySum+accuracy;
                //正确率
                BigDecimal b = new BigDecimal(accuracy);
                // java保留两位小数问题：四舍五入
                scoreReport.setAccuracy(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                //作业名称
                scoreReport.setHomeworkName(homeworkCollect.getHomeworkName());
                scoreReportParamList.add(scoreReport);
            }
            double averageAccuracy=0;
            if(accuracySum>0) {
                //平均正确率
                 averageAccuracy = accuracySum/(homeworkCollectList.size());
                BigDecimal c = new   BigDecimal(averageAccuracy);
                // java保留两位小数问题：四舍五入
                scoreReportVo.setAverageAccuracy(c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            } else {
                 averageAccuracy = 0;
                scoreReportVo.setAverageAccuracy(averageAccuracy);
            }
//            scoreReportVo.setAverageAccuracy(averageAccuracy);
            scoreReportVo.setScoreReportParamList(scoreReportParamList);
        }
        return scoreReportVo;
    }

    //使用String的split 方法
    public static String[] convertStrToArray(String str){
        String[] strArray = null;
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray
        return strArray;
    }

    //计算有效时间
    protected int computeDate(Date deadline ,int addDate) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date systemTime = new Date();
        long m = (systemTime.getTime())-(deadline.getTime());
        int i = (int)(m / (1000 * 60 * 60 * 24));
        int validDay = addDate -i;

        return validDay;
    }
}
