package com.cn.mogo.sunEdu.core.service.Impl;

import com.cn.mogo.sunEdu.core.dao.AnswerSheetMapper;
import com.cn.mogo.sunEdu.core.dao.AnswersMapper;
import com.cn.mogo.sunEdu.core.dao.HomeworkCollectMapper;
import com.cn.mogo.sunEdu.core.dao.StuCollectionMapperExt;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionDetailParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionInsertParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionParams;
import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionUpdateParams;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionDetailVo;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionWorkVo;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.result.BizResultCodeEnum;
import com.cn.mogo.sunEdu.core.service.StudentCollectionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "studentCollectionService")
public class StudentCollectionServiceImpl implements StudentCollectionService {

    @Autowired
    private StuCollectionMapperExt stuCollectionMapperExt;
    @Autowired
    private AnswerSheetMapper answerSheetMapper;
    @Autowired
    private HomeworkCollectMapper homeworkCollectMapper;
    @Autowired
    private AnswersMapper answersMapper;

    public BizResult<List<StudentCollectionWorkVo>> selectStudentCollectionList(StudentCollectionParams params) {
        List<StudentCollectionWorkVo> collList = stuCollectionMapperExt.selectStudentCollectionList(params);
        return BizResult.success(collList);
    }

    public BizResult<String> insertStudentCollection(StudentCollectionInsertParams insertParams) {
        //是否已经收藏过该作业
        Integer stuId = insertParams.getStudentId();
        Integer worId = insertParams.getWorkId();
        StuCollectionExample example = new StuCollectionExample();
        example.setOrderByClause("id desc limit 1");
        StuCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andCollectionIdEqualTo(worId)
                .andCollectionTypesEqualTo(1)
                .andStudentIdEqualTo(stuId);
        List<StuCollection> stuList = stuCollectionMapperExt.selectByExample(example);
        if (CollectionUtils.isEmpty(stuList)) {
            //新增收藏
            StuCollection record = new StuCollection();
            record.setCollectionTypes(Integer.valueOf(1)); //目前只能收藏作业
            record.setCollectionId(insertParams.getWorkId());
            record.setStudentId(insertParams.getStudentId());
            record.setSubCollectionIds(StringUtils.join(insertParams.getSubCollectionIds(), ","));
            stuCollectionMapperExt.insertSelective(record);
        } else {
            StuCollection origin = stuList.get(0);
            Set<String> insetSet = getSubIdSet(origin.getSubCollectionIds().split(","), insertParams.getSubCollectionIds());
            origin.setCollectionStatus(1);
            origin.setSubCollectionIds(StringUtils.join(insetSet, ","));
            stuCollectionMapperExt.updateByPrimaryKeySelective(origin);
        }
        return new BizResult<String>(BizResultCodeEnum.SUCCESS);
    }

    private Set<String> getSubIdSet(String[] oriSubIds, Set<String> inIds) {
        Set<String> resultSet = new HashSet<String>();
        if (oriSubIds != null && oriSubIds.length > 0) {
            for (String orId : oriSubIds) {
                if (StringUtils.isNotBlank(orId)) {
                    resultSet.add(orId);
                }
            }
            for (String inId : inIds) {
                if (StringUtils.isNotBlank(inId) && !resultSet.contains(StringUtils.trim(inId))) {
                    resultSet.add(inId);
                }
            }
        }
        return resultSet;
    }

    /**
     * 取消收藏
     *
     * @param updateParams
     * @return
     */
    public BizResult updateStudentCollection(StudentCollectionUpdateParams updateParams) {
        StuCollectionExample example = new StuCollectionExample();
        example.setOrderByClause("id desc limit 1");
        StuCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andCollectionIdEqualTo(updateParams.getWorkId())
                .andStudentIdEqualTo(updateParams.getStudentId());
        List<StuCollection> resultList = stuCollectionMapperExt.selectByExample(example);
        if (CollectionUtils.isNotEmpty(resultList) && Integer.valueOf(1).equals(resultList.get(0).getCollectionStatus())) {
            StuCollection origin = resultList.get(0);
            StuCollection updateSc = this.warpUpdateStuCollection(origin, updateParams);
            stuCollectionMapperExt.updateByPrimaryKeySelective(updateSc);
        }
        return BizResult.success(BizResultCodeEnum.SUCCESS);
    }

    /**
     * 根据传入的要取消的收藏编号，从原来的收藏编号中移除需要取消的收藏编号
     *
     * @param origin
     * @param updateParams
     * @return
     */
    private StuCollection warpUpdateStuCollection(StuCollection origin, StudentCollectionUpdateParams updateParams) {
        StuCollection updateSc = new StuCollection();
        Set<String> unSubIdSet = updateParams.getUnSubCollectionIds();
        Set<String> originIds = new HashSet<String>(Arrays.asList(origin.getSubCollectionIds().split(",")));
        for (String unId : unSubIdSet) {
            unId = StringUtils.trimToEmpty(unId);
            if (StringUtils.isNotBlank(unId) && originIds.contains(unId)) {
                originIds.remove(unId);
            }
        }
        if (CollectionUtils.isEmpty(originIds)) {
            updateSc.setCollectionStatus(0);
        }
        String s = StringUtils.join(originIds, ",");
        updateSc.setSubCollectionIds(s);
        updateSc.setId(origin.getId());
        return updateSc;
    }

    public BizResult<StudentCollectionDetailVo> selectStudentCollectionDetail(StudentCollectionDetailParams detailParams) {
        //1,获取收藏作业的题目详细信息列表
        StuCollection scoll = this.stuCollectionMapperExt.selectByPrimaryKey(detailParams.getId());
        if (scoll == null || StringUtils.isBlank(scoll.getSubCollectionIds())) {
            return new BizResult<StudentCollectionDetailVo>(BizResultCodeEnum.NOT_EXISTED);
        }
        //收藏作业编号
        Integer homeworkId = scoll.getCollectionId();
        List<Integer> idList = new ArrayList<Integer>();
        String[] subids = scoll.getSubCollectionIds().split(",");
        for (String id : subids) {
            if (StringUtils.isNotBlank(id)) {
                idList.add(Integer.valueOf(StringUtils.trim(id)));
            }
        }
        List<AnswerSheet> answerList = answerSheetMapper.selectAnswerSheetListByIds(idList);
        //2，获取收藏作业老师发布的答案列表
        Answers an = answersMapper.selectAnswerByHomeworkId(homeworkId);
        //3，获取作业的题目列表
        HomeworkCollect hc = homeworkCollectMapper.selectByPrimaryKey(homeworkId);
        //答案列表
        List<String> anserUrlList = new ArrayList<String>();
        if( System.currentTimeMillis() > hc.getDeadline().getTime() ){
            anserUrlList = getList(an == null ? null : an.getAnswersUrl());
        }
        //作业列表
        List<String> workUrlList = getList(hc == null ? null : hc.getHomeworkPic());
        StudentCollectionDetailVo detailVo = new StudentCollectionDetailVo();
        //备注
        String remark = (hc == null? null :hc.getRemark());
        detailVo.setAnswerDetailList(answerList);
        detailVo.setAnserUrlList(anserUrlList);
        detailVo.setWorkUrlList(workUrlList);
        detailVo.setRemark(remark);
        return BizResult.success(detailVo);
    }

    private List<String> getList(String urls) {
        List<String> urlList = new ArrayList<String>();
        if (StringUtils.isNotBlank(urls)) {
            String[] asUrls = urls.split(";");
            for (String url : asUrls) {
                url = StringUtils.trimToEmpty(url);
                if (StringUtils.isNotBlank(url)) {
                    urlList.add(url);
                }
            }
        }
        return urlList;
    }
}
