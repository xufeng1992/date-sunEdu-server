package com.cn.mogo.sunEdu.core.service.Impl;

import com.cn.mogo.sunEdu.core.model.params.AnswerAccuracyParams;
import com.cn.mogo.sunEdu.core.model.params.AnswerCheckParams;
import com.cn.mogo.sunEdu.core.model.vo.AnswerAccuracyVo;
import com.cn.mogo.sunEdu.core.model.vo.AnswerCheckVo;
import com.cn.mogo.sunEdu.core.result.BizResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.mogo.sunEdu.core.dao.AnswerSheetMapper;
import com.cn.mogo.sunEdu.core.model.AnswerSheet;
import com.cn.mogo.sunEdu.core.model.params.AnswerSheetParams;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.service.AnswersSheetService;

import java.util.List;

/**
 * Created by FE on 2016/6/27.
 */
@Service
public class AnswersSheetServiceImpl implements AnswersSheetService {

    @Autowired
    private AnswerSheetMapper answerSheetMapper;

    public BizResult updateDetailByPrimaryKeySelective(AnswerSheetParams sheetParams) {
        BizResult result = null;
        AnswerSheet originAnswer = answerSheetMapper.selectByPrimaryKey(sheetParams.getId());
        if( originAnswer == null ){
            result = new BizResult(BizResultCodeEnum.NOT_EXISTED);
        }else {
            answerSheetMapper.updateDetailByPrimaryKeySelective(sheetParams);
            result = BizResult.success();
        }
        return result;
    }

    public BizResult<List<AnswerCheckVo>> selectCheckedAnswerCommitList(AnswerCheckParams checkParams) {
        List<AnswerCheckVo> voList = answerSheetMapper.selectCheckedAnswerCommitList(checkParams);
        return BizResult.success(voList);
    }

    public BizResult<List<AnswerAccuracyVo>> selectCheckedAnswerAccuracyList(AnswerAccuracyParams accuracyParams) {
        List<AnswerAccuracyVo> voList = answerSheetMapper.selectCheckedAnswerAccuracyList(accuracyParams);
        return BizResult.success(voList);
    }
}
