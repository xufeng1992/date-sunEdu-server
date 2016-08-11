package com.cn.mogo.sunEdu.core.service;

import java.util.List;

import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionDetailParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionInsertParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionUpdateParams;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionDetailVo;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionWorkVo;
import com.cn.mogo.sunEdu.core.result.BizResult;

/**
 * @author FE
 *         学生收藏服务接口
 */
public interface StudentCollectionService {


    /**
     * 添加收藏
     *
     * @param insertParams
     * @return
     */
    BizResult<String> insertStudentCollection(StudentCollectionInsertParams insertParams);

    /**
     * 取消收藏
     *
     * @param updateParams
     * @return
     */
    BizResult updateStudentCollection(StudentCollectionUpdateParams updateParams);

    /**
     * 获取学生收藏列表
     *
     * @param params
     * @return
     */
    BizResult<List<StudentCollectionWorkVo>> selectStudentCollectionList(StudentCollectionParams params);


    /**
     * 获取学生收藏作业详细信息
     *
     * @param detailParams
     * @return
     */
    BizResult<StudentCollectionDetailVo> selectStudentCollectionDetail(StudentCollectionDetailParams detailParams);


}
