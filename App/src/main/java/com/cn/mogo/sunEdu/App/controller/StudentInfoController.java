package com.cn.mogo.sunEdu.App.controller;/**
 * Created by Administrator on 2016/7/7 0007.
 */

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.utils.SubjectEnum;
import com.cn.mogo.sunEdu.core.model.PushDo;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.params.student.ScoreReportParam;
import com.cn.mogo.sunEdu.core.model.vo.student.ScoreReportVo;
import com.cn.mogo.sunEdu.core.service.AnswersService;
import com.cn.mogo.sunEdu.core.service.HomeworkCollectService;
import com.cn.mogo.sunEdu.core.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StudentInfoController
 *
 * @author xufeng
 * @date 2016/7/7 0007
 */
@RequestMapping(value = "/api/app")
@RestController
public class StudentInfoController {

    @Autowired
    private AnswersService answersService;
    @Autowired
    private HomeworkCollectService homeworkCollectService;
    @Autowired
    private PushService pushService;

    /**
     * 统计报表中科目名的取得
     * @param request
     * @return
     */
    @RequestMapping(value ="/getSubjectName",method = RequestMethod.POST)
    public BaseJSONResponse<HashMap<Integer,String>> getSubjectName(@RequestBody BaseJSONRequest<Student> request) {
        BaseJSONResponse<HashMap<Integer,String>> response = new BaseJSONResponse<HashMap<Integer,String>>();
        String token = request.getToken();
        Student student = request.getBusiness();
        Integer studentId = student.getStudentId();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else if((student.getStudentId())<0){
            response.setMessage("学生ID不正确");
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            List<Integer> subjectIdList = new ArrayList<Integer>();
            //科目
            subjectIdList = answersService.getSubjectName(studentId);
            HashMap<Integer,String> hash = new HashMap<Integer,String>();
            for(Integer subjectId :subjectIdList) {
                if (subjectId!=null) {
                    SubjectEnum subjectEnum = SubjectEnum.getSubjecName(subjectId);
                    hash.put(subjectEnum.getSubject(), subjectEnum.getSubjectName());
                }
            }
            response.setData(hash);
            response.setMessage("获取个人信息成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
        }
        return response;
    }


    /**
     * 统计报表中统计数据取得
     * @param request
     * @return
     */
    @RequestMapping(value = "/stu/scoreReport",method = RequestMethod.POST)
    public BaseJSONResponse<ScoreReportVo> scoreReport(@RequestBody BaseJSONRequest<ScoreReportParam> request) {
        BaseJSONResponse<ScoreReportVo> response = new BaseJSONResponse<ScoreReportVo>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            ScoreReportParam scoreReportParam = request.getBusiness();
            //学生Id
            Integer studentId = scoreReportParam.getStudentId();
            //科目Id
            Integer subjectId= scoreReportParam.getSubjectId();
            if (studentId == null || subjectId==null) {
                response.setMessage("学生Id或者科目Id不能为空");
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
            } else{
                ScoreReportVo scoreReportVo = new ScoreReportVo();
                scoreReportVo =  homeworkCollectService.scoreReport(scoreReportParam);
                response.setData(scoreReportVo);
                response.setMessage("学生统计报告获取成功");
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
            }

        }
        return response;
    }

    /**
     * 我的消息
     * @param request
     * @return
     */
    @RequestMapping(value="/getPushInfo",method = RequestMethod.POST)
    public BaseJSONResponse<List<PushDo>> getPushInfo(@RequestBody BaseJSONRequest<Student> request) {
        BaseJSONResponse<List<PushDo>> response = new BaseJSONResponse<List<PushDo>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Student student = request.getBusiness();
            //学生Id
            int studentId = student.getStudentId();
            List<PushDo> pushDoList = pushService.getPushInfo(studentId);
            response.setData(pushDoList);
            response.setMessage("推送消息获取成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
        }
        return response;
    }

}
