package com.cn.mogo.sunEdu.App.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cn.mogo.sunEdu.core.model.params.student.*;
import com.cn.mogo.sunEdu.core.model.vo.student.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cn.mogo.sunEdu.App.constant.PathConstant;
import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.heapler.ConvertorHelper;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.utils.FileUtil;
import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.params.AnswerParams;
import com.cn.mogo.sunEdu.core.model.params.student.HomeworkDetailParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentHomeWorkStatusParams;
import com.cn.mogo.sunEdu.core.model.params.student.UpdateAnswerParams;
import com.cn.mogo.sunEdu.core.model.vo.student.AnswerUpdateVo;
import com.cn.mogo.sunEdu.core.model.vo.student.HomeworkDetailVo;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentHomeWorkStatusVo;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.service.StudentHomeWorkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by FE on 2016/7/5.
 */
@RestController
@RequestMapping(value = "/api/app")
public class StudentWorkController extends BasicController {

    @Autowired
    private StudentHomeWorkService studentHomeWorkService;

    /**
     * 查询学生作业列表显示【学生待批，已批作业】
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/student/homework/list", method = RequestMethod.POST)
    public BaseJSONResponse selectStudentHomeWorkList(@RequestBody StudentHomeWorkStatusParams params) {
        BaseJSONResponse res = verifyTokenParams(params);
        if (res.isSuccess()) {
            BizResult<List<StudentHomeWorkStatusVo>> bizResult = studentHomeWorkService.selectStudentHomeWorkList(params);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    /**
     * 查询学生作业列表显示【学生待批，已批作业】
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/student/homework/detail", method = RequestMethod.POST)
    public BaseJSONResponse selectStudentHomeWorkDetail(@RequestBody HomeworkDetailParams params) {
        BaseJSONResponse res = verifyTokenParams(params);
        if (res.isSuccess()) {
            BizResult<HomeworkDetailVo> bizResult = studentHomeWorkService.selectStudentHomeWorkDetail(params);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    /**
     * 查询学生作业答案列表显示
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/student/answer/list", method = RequestMethod.POST)
    public BaseJSONResponse selectStudentHomeWorkAnswerList(@RequestBody AnswerParams params) {
        BaseJSONResponse res = verifyTokenParams(params);
        if (res.isSuccess()) {
            BizResult<AnswerUpdateVo> bizResult = studentHomeWorkService.selectStudentHomeWorkAnswerList(params);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    /**
     * 学生发布答案
     *
     * @return
     */
    @RequestMapping(value = "/student/answer/insert", method = RequestMethod.POST)
    public BaseJSONResponse insertStudentHomeWorkAnswer(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "workId", required = true) Integer workId,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            @RequestParam(value = "answerFiles", required = true) MultipartFile[] answerFiles) {
        BaseJSONResponse resToken = verifyToken(token);
        BaseJSONResponse resFile = verifyAnswerFile(answerFiles);
        if (!resToken.isSuccess()) {
            return resToken;
        }
        if (!resFile.isSuccess()) {
            return resFile;
        }
        Map<String, String> answerPathMap = FileUtil.saveAnswerFile(PathConstant.STUDENT_ANSWER_BASE_PATH, answerFiles, false);
        List<UpdateAnswerParams> insertList = this.warpUpdateAnswerParams(workId, studentId, answerPathMap);
        BizResult<String> bizResult = studentHomeWorkService.insertStudentHomeWorkAnswer(insertList);
        return ConvertorHelper.convertorBizToResponse(bizResult);
    }


    /**
     * 学生修改答案
     *
     * @return
     */
    @RequestMapping(value = "/student/answer/update", method = RequestMethod.POST)
    public BaseJSONResponse updateStudentHomeWorkAnswer(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "workId", required = true) Integer workId,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            @RequestParam(value = "answerFiles", required = true) MultipartFile[] answerFiles) {

        BaseJSONResponse resToken = verifyToken(token);
        BaseJSONResponse resFile = verifyAnswerFile(answerFiles);
        if (!resToken.isSuccess()) {
            return resToken;
        }
        if (!resFile.isSuccess()) {
            return resFile;
        }
        Map<String, String> answerPathMap = FileUtil.saveAnswerFile(PathConstant.STUDENT_ANSWER_BASE_PATH, answerFiles, false);
        List<UpdateAnswerParams> updateList = this.warpUpdateAnswerParams(workId, studentId, answerPathMap);
        BizResult<String> bizResult = studentHomeWorkService.updateStudentHomeWorkAnswer(updateList);
        return ConvertorHelper.convertorBizToResponse(bizResult);
    }

    /**
     * 校验答案文件
     *
     * @param answerFiles
     * @return
     */
    private BaseJSONResponse verifyAnswerFile(MultipartFile[] answerFiles) {
        BaseJSONResponse res = new BaseJSONResponse();
        if (answerFiles == null || answerFiles.length == 0) {
            res.setMessage("答案不能为空！");
            res.setSuccess(StatusConstant.FALSE);
            res.setStatusCode(StatusConstant.RETURN_FAILED);
            return res;
        } else {
            res.setSuccess(StatusConstant.SUCCESS);
        }
        return res;
    }

    /**
     * @param workId
     * @param studentId
     * @param answerPathMap
     * @return
     */
    private List<UpdateAnswerParams> warpUpdateAnswerParams(Integer workId, Integer studentId, Map<String, String> answerPathMap) {
        List<UpdateAnswerParams> warpList = new ArrayList<UpdateAnswerParams>();
        Date date = new Date();
        for (Map.Entry<String, String> entry : answerPathMap.entrySet()) {
            String subjectId = StringUtils.trimToEmpty(entry.getKey());
            String answerPath = StringUtils.trimToEmpty(entry.getValue());
            UpdateAnswerParams updateParams = new UpdateAnswerParams();
            updateParams.setWorkId(workId);
            updateParams.setStudentId(studentId);
            updateParams.setPicAddress(answerPath);
            updateParams.setSubmitTime(date);
            updateParams.setSubjectId(Integer.valueOf(subjectId));
            warpList.add(updateParams);
        }
        return warpList;
    }

    /**
     * 学生端未上传答案列表显示【我的中上传答案】
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/student/yetUpload/answer", method = RequestMethod.POST)
    public BaseJSONResponse<List<HomeworkCollect>> selectYetUpload(@RequestBody BaseJSONRequest<Student> request) {

        BaseJSONResponse<List<HomeworkCollect>> response = new BaseJSONResponse<List<HomeworkCollect>>();
        Student student = request.getBusiness();
        String token = request.getToken();

        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else if(student!=null && (student.getStudentId())>0){
            List<HomeworkCollect> homeWorkControllerList = new ArrayList<HomeworkCollect>();
            //取得未发布答案列表
            homeWorkControllerList = studentHomeWorkService.selectYetUpload(student.getStudentId());
            response.setData(homeWorkControllerList);
            response.setMessage("未发布答案列表取得成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
        } else {
            response.setMessage("参数不正确");
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(StatusConstant.FALSE);
        }
        return response;
    }

    /**
     * 学生查看老师发布作业的答案
     *
     * @return
     */
    @RequestMapping(value = "/student/answer/lookup", method = RequestMethod.POST)
    public BaseJSONResponse selectHomeWorkAnswerLookup(@RequestBody HomeWorkAnswerLookupParams lookupParams) {
        BaseJSONResponse res = verifyTokenParams(lookupParams);
        if (res.isSuccess()) {
            BizResult<HomeWorkAnswerLookupVo> bizResult = studentHomeWorkService.selectHomeWorkAnswerLookup(lookupParams);
            return ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }


    /**
     * 学生查看老师发布作业的答案
     *
     * @return
     */
    @RequestMapping(value = "/student/answercheck/list", method = RequestMethod.POST)
    public BaseJSONResponse selectHomeWorkAnswerCheckedList(@RequestBody HomeWorkAnswerLookupParams lookupParams) {
        BaseJSONResponse res = verifyTokenParams(lookupParams);
        if (res.isSuccess()) {
            BizResult<List<AnswerCheckListResultVo>> bizResult = studentHomeWorkService.selectHomeWorkAnswerCheckedList(lookupParams);
            return ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    /**
     * 学生查看老师发布作业的答案
     *
     * @return
     */
    @RequestMapping(value = "/student/answercheck/detail", method = RequestMethod.POST)
    public BaseJSONResponse selectHomeWorkAnswerCheckedDetail(@RequestBody AnswerCheckDetailParams detailParams) {
        BaseJSONResponse res = verifyTokenParams(detailParams);
        if (res.isSuccess()) {
            BizResult<AnswerCheckDetailResultVo> bizResult = studentHomeWorkService.selectHomeWorkAnswerCheckedDetail(detailParams);
            return ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }
}
