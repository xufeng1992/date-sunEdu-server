package com.cn.mogo.sunEdu.core.model.vo.student;/**
 * Created by Administrator on 2016/7/11 0011.
 */

import com.cn.mogo.sunEdu.core.model.params.student.ScoreReportParam;

import java.io.Serializable;
import java.util.List;

/**
 * ScoreReportVo
 *
 * @author xufeng
 * @date 2016/7/11 0011
 */
public class ScoreReportVo implements Serializable {

    //平均准确率
    private double averageAccuracy;
    //报告数据
    private List<ScoreReportParam> scoreReportParamList;

    public double getAverageAccuracy() {
        return averageAccuracy;
    }

    public void setAverageAccuracy(double averageAccuracy) {
        this.averageAccuracy = averageAccuracy;
    }

    public List<ScoreReportParam> getScoreReportParamList() {
        return scoreReportParamList;
    }

    public void setScoreReportParamList(List<ScoreReportParam> scoreReportParamList) {
        this.scoreReportParamList = scoreReportParamList;
    }
}
