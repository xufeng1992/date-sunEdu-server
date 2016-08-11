package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.params.AnswerAccuracyParams;
import com.cn.mogo.sunEdu.core.model.params.AnswerCheckParams;
import com.cn.mogo.sunEdu.core.model.params.AnswerSheetParams;
import com.cn.mogo.sunEdu.core.model.vo.AnswerAccuracyVo;
import com.cn.mogo.sunEdu.core.model.vo.AnswerCheckVo;
import com.cn.mogo.sunEdu.core.result.BizResult;

import java.util.List;

/**
 * Created by FE on 2016/6/27.
 */
public interface AnswersSheetService {

    /**
     * 修改作业信息
     * @param sheetParams
     * @return
     */
    BizResult<String> updateDetailByPrimaryKeySelective(AnswerSheetParams sheetParams);

    /**
     * 已批改作业提交时间列表
     * @param checkParams
     * @return
     */
    BizResult<List<AnswerCheckVo>> selectCheckedAnswerCommitList(AnswerCheckParams checkParams );

    /**
     * 已批改作业 正确率列表
     * @param accuracyParams
     * @return
     */
    BizResult<List<AnswerAccuracyVo>> selectCheckedAnswerAccuracyList(AnswerAccuracyParams accuracyParams );
}
