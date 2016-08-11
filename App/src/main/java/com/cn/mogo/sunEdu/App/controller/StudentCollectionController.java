package com.cn.mogo.sunEdu.App.controller;

import com.cn.mogo.sunEdu.App.heapler.ConvertorHelper;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionDetailParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionInsertParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionUpdateParams;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionDetailVo;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionWorkVo;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.service.StudentCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by FE on 2016/7/10.
 */
@RestController
@RequestMapping(value = "/api/app")
public class StudentCollectionController extends BasicController {

    @Autowired
    private StudentCollectionService studentCollectionService;

    /**
     * 学生收藏作业
     * @param insertParams
     * @return
     */
    @RequestMapping(value = "/student/collection/insert", method = RequestMethod.POST)
    public BaseJSONResponse insertStudentCollection(@RequestBody StudentCollectionInsertParams insertParams) {
        BaseJSONResponse res = verifyTokenParams(insertParams);
        if (res.isSuccess()) {
            BizResult<String> bizResult = studentCollectionService.insertStudentCollection(insertParams);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    /**
     * 学生取消收藏作业
     * @param updateParams
     * @return
     */
    @RequestMapping(value = "/student/collection/update", method = RequestMethod.POST)
    public BaseJSONResponse updateStudentCollection(@RequestBody StudentCollectionUpdateParams updateParams) {
        BaseJSONResponse res = verifyTokenParams(updateParams);
        if (res.isSuccess()) {
            BizResult<String> bizResult = studentCollectionService.updateStudentCollection(updateParams);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    @RequestMapping(value = "/student/collection/list")
    public BaseJSONResponse selectStudentCollectionList(@RequestBody StudentCollectionParams params) {
        BaseJSONResponse res = verifyTokenParams(params);
        if (res.isSuccess()) {
            BizResult<List<StudentCollectionWorkVo>> bizResult = studentCollectionService.selectStudentCollectionList(params);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

    @RequestMapping(value = "/student/collection/detail")
    public BaseJSONResponse selectStudentCollectionDetail(@RequestBody StudentCollectionDetailParams detailParams) {
        BaseJSONResponse res = verifyTokenParams(detailParams);
        if (res.isSuccess()) {
            BizResult<StudentCollectionDetailVo> bizResult = studentCollectionService.selectStudentCollectionDetail(detailParams);
            res = ConvertorHelper.convertorBizToResponse(bizResult);
        }
        return res;
    }

}
