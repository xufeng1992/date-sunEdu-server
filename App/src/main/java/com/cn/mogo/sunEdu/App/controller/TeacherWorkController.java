package com.cn.mogo.sunEdu.App.controller;

import com.cn.mogo.sunEdu.App.constant.PathConstant;
import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.utils.FileUtil;
import com.cn.mogo.sunEdu.core.common.PageableContent;
import com.cn.mogo.sunEdu.core.model.AnswerSheet;
import com.cn.mogo.sunEdu.core.model.params.*;
import com.cn.mogo.sunEdu.core.model.vo.*;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.result.BizResultCodeEnum;
import com.cn.mogo.sunEdu.core.service.AnswersSheetService;
import com.cn.mogo.sunEdu.core.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by FE on 2016/6/21. 老师作业控制器主要有
 */
@RestController
@RequestMapping(value = "/api/app")
@SuppressWarnings({"rawtypes", "unchecked"})
public class TeacherWorkController extends BasicController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AnswersSheetService answersSheetService;

    /**
     * 获取老师待批，已批作业集信息[待批作业 status = 0 ，已批作业 status = 1]
     *
     * @param workParams
     * @return
     */
    @RequestMapping(value = "/homework/list")
    public BaseJSONResponse selectWorkList(@RequestBody HomeWorkParams workParams) {
        BaseJSONResponse res = verifyToken(workParams.getToken());
        boolean isTrue = workParams.verifyParams();
        if (!res.isSuccess()) {
            return res;
        } else if (!isTrue) {
            res = new BaseJSONResponse();
            res.setStatusCode(StatusConstant.RETURN_FAILED);
            res.setSuccess(StatusConstant.FALSE);
            res.setMessage(workParams.getMsg());
            return res;
        }
        res = new BaseJSONResponse();
        PageableContent<List<HomeWorkStatusVo>> resPage = this.teacherService.selectTeacherHomeWorkStatusList(workParams);
        res.setStatusCode(StatusConstant.RETURN_SUCCESS);
        res.setSuccess(StatusConstant.SUCCESS);
        res.setMessage("成功获取老师的作业列表");
        res.setData(resPage);
        return res;
    }

    /**
     * 获取作业集下学生完成作业的情况
     * 学生未完成批阅/已完成批阅
     *
     * @param studentParams
     * @return
     */
    @RequestMapping(value = "/homework/students")
    public BaseJSONResponse selectStudentsWorks(
            @RequestBody StudentParams studentParams) {
        BaseJSONResponse res = verifyToken(studentParams.getToken());
        if (res.isSuccess()) {
            PageableContent<List<StudentVo>> resPage = this.teacherService.selectStudentAnswerSheetPages(studentParams);
            res = new BaseJSONResponse();
            res.setStatusCode(StatusConstant.RETURN_SUCCESS);
            res.setSuccess(StatusConstant.SUCCESS);
            res.setMessage("成功获取学生作业信息列表");
            res.setData(resPage);
        }
        return res;
    }

    /**
     * 获取学生，作业集的答案列表
     *
     * @param answerParams
     * @return
     */
    @RequestMapping(value = "/homework/answers")
    public BaseJSONResponse selectAnswersWorks(@RequestBody AnswerParams answerParams) {
        BaseJSONResponse res = verifyToken(answerParams.getToken());
        if (!res.isSuccess()) {
            return res;
        }
        if (!answerParams.verifyParams()) {
            res.setStatusCode(StatusConstant.RETURN_FAILED);
            res.setSuccess(StatusConstant.FALSE);
            res.setMessage(answerParams.getMsg());
            return res;
        }
        List<AnswerVo> resList = teacherService.selectAnswerSheetList(answerParams);
        res.setData(resList);
        res.setStatusCode(StatusConstant.RETURN_SUCCESS);
        res.setSuccess(StatusConstant.SUCCESS);
        res.setMessage("成功获取答案列表");
        return res;
    }

    /**
     * 学生作业答案详细信息展示
     */
    @RequestMapping(value = "/answersheet/details")
    public BaseJSONResponse selectAnswersDetails(@RequestBody AnswerSheetParams sheetParams) {
        BaseJSONResponse res = verifyToken(sheetParams.getToken());
        if (!res.isSuccess()) {
            return res;
        }
        if (!sheetParams.verifyParams()) {
            res.setSuccess(StatusConstant.FALSE);
            res.setMessage(sheetParams.getMsg());
            res.setData(null);
            return res;
        }
        BizResult<AnswerSheet> bizResult = teacherService.selectAnswerSheetDetails(sheetParams.getId());
        res.setSuccess(bizResult.isSuccess());
        res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        res.setMessage(bizResult.getMessage());
        res.setData(bizResult.getResultObj());
        return res;
    }

    /**
     * 老师批阅学生作业答案
     *
     * @return
     */
    @RequestMapping(value = "answersheet/update", method = RequestMethod.POST)
    public BaseJSONResponse updateAnswerSheet(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "voiceFile", required = false) MultipartFile voiceFile,
            @RequestParam(value = "commentText", required = false) String commentText,
            @RequestParam(value = "commentFile", required = true) MultipartFile commentFile,  //老师批改的作业图片
            @RequestParam(value = "score", required = true) Double score) throws IOException {
        BaseJSONResponse res = verifyToken(token);
        if (commentFile == null) {
            res.setMessage("commentFile " + BizResultCodeEnum.NULL_ARGUMENT.getDescription());
            res.setStatusCode(BizResultCodeEnum.NULL_ARGUMENT.getCode());
            res.setSuccess(false);
            return res;
        }
        if (res.isSuccess()) {
            //拷贝上传的文件
            String commentFilePath = "", voiceFilePath = "";
            // 保存路径
            commentFilePath = FileUtil.saveFile(PathConstant.HW_IMAGE_BASE_PATH, commentFile, false);
            if (voiceFile != null) {
                voiceFilePath = FileUtil.saveFile(PathConstant.HW_IMAGE_BASE_PATH, voiceFile, false);
            }
            AnswerSheetParams params = new AnswerSheetParams();
            params.setId(id);
            params.setPicAddress(commentFilePath);
            params.setTeacherComment(commentText);
            params.setTeacherVoice(voiceFilePath);
            params.setScore(score);
            params.setStatus(2);
            BizResult bizResult = answersSheetService.updateDetailByPrimaryKeySelective(params);
            res.setSuccess(bizResult.isSuccess());
            res.setData(bizResult.getResultObj());
            res.setMessage(bizResult.getMessage());
            res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        }
        return res;
    }


    /**
     * 老师已批改作业 提交时间列表
     */
    @RequestMapping(value = "/answersheet/commitlist")
    public BaseJSONResponse selectCheckedAnswerCommitList(@RequestBody AnswerCheckParams answerParams) {
        BaseJSONResponse res = verifyToken(answerParams.getToken());
        if (res.isSuccess()) {
            BizResult<List<AnswerCheckVo>> bizResult = answersSheetService.selectCheckedAnswerCommitList(answerParams);
            res.setSuccess(bizResult.isSuccess());
            res.setData(bizResult.getResultObj());
            res.setMessage(bizResult.getMessage());
            res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        }
        return res;
    }

    /**
     * 老师已批改作业 正确率列表
     */
    @RequestMapping(value = "/answersheet/accuracylist")
    public BaseJSONResponse selectCheckedAnswerList(@RequestBody AnswerAccuracyParams accuracyParams) {
        BaseJSONResponse res = verifyToken(accuracyParams.getToken());
        if (res.isSuccess()) {
            BizResult<List<AnswerAccuracyVo>> bizResult = answersSheetService.selectCheckedAnswerAccuracyList(accuracyParams);
            res.setSuccess(bizResult.isSuccess());
            res.setData(bizResult.getResultObj());
            res.setMessage(bizResult.getMessage());
            res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        }
        return res;
    }


    /**
     * 未提交作业学生名单接口
     */
    @RequestMapping(value = "/homework/uncommitlist")
    public BaseJSONResponse selectUnCommitListStudent(@RequestBody StudentHomeWorkCommitParams commitParams) {
        BaseJSONResponse res = verifyToken(commitParams.getToken());
        if (res.isSuccess()) {
            BizResult<List<StudentCommitVo>> bizResult = teacherService.selectStudentHomeWorkUnCommitList(commitParams);
            res.setSuccess(bizResult.isSuccess());
            res.setData(bizResult.getResultObj());
            res.setMessage(bizResult.getMessage());
            res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        }
        return res;
    }

    /**
     * 作业集学生学生及格率，最高分，最低分信息统计
     */
    @RequestMapping(value = "/homework/statistics")
    public BaseJSONResponse selectHomeworkStatistics(@RequestBody HomeworkStatisticsParams statisticsParams) {
        BaseJSONResponse res = verifyToken(statisticsParams.getToken());
        if( !statisticsParams.verifyParams() ){
            res.setStatusCode(BizResultCodeEnum.ILLEGAL_ARGUMENT.getCode());
            res.setMessage(BizResultCodeEnum.ILLEGAL_ARGUMENT.getDescription() + statisticsParams.getMsg());
            res.setSuccess(false);
            return res ;
        }
        if (res.isSuccess()) {
            BizResult<HomeworkStatisticsResultVo> bizResult = teacherService.selectHomeworkStatistics(statisticsParams);
            res.setSuccess(bizResult.isSuccess());
            res.setData(bizResult.getResultObj());
            res.setMessage(bizResult.getMessage());
            res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        }
        return res;
    }

}
