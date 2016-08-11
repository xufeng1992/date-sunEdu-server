package com.cn.mogo.sunEdu.App.json.model;/**
 * Created by Administrator on 2016/6/17 0017.
 */

import com.cn.mogo.sunEdu.core.model.AnswerSheet;

import java.util.List;

/**
 * historyHomeBean
 *
 * @author xufeng
 * @date 2016/6/17 0017
 */
public class HistoryHomeBean {
    //总题数
    private int totalNumber;
    //上交人数
    private int finishNum;
    //总人数
    private int studentNum;
    //有效天数
    private int validDay;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getValidDay() {
        return validDay;
    }

    public void setValidDay(int validDay) {
        this.validDay = validDay;
    }
    //    //学生答题集
//    private List<AnswerSheet> answerSheetList;
}
