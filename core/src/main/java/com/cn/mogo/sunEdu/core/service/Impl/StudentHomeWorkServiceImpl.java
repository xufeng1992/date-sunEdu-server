package com.cn.mogo.sunEdu.core.service.Impl;

import com.cn.mogo.sunEdu.core.dao.AnswerSheetMapper;
import com.cn.mogo.sunEdu.core.dao.AnswersMapper;
import com.cn.mogo.sunEdu.core.dao.HomeworkCollectMapper;
import com.cn.mogo.sunEdu.core.dao.StuCollectionMapperExt;
import com.cn.mogo.sunEdu.core.model.HomeworkCollect;
import com.cn.mogo.sunEdu.core.model.StuCollection;
import com.cn.mogo.sunEdu.core.model.StuCollectionExample;
import com.cn.mogo.sunEdu.core.model.params.AnswerParams;
import com.cn.mogo.sunEdu.core.model.params.student.*;
import com.cn.mogo.sunEdu.core.model.vo.AnswerVo;
import com.cn.mogo.sunEdu.core.model.vo.student.*;
import com.cn.mogo.sunEdu.core.result.BizResult;
import com.cn.mogo.sunEdu.core.result.BizResultCodeEnum;
import com.cn.mogo.sunEdu.core.service.StudentHomeWorkService;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by FE on 2016/7/5.
 */
@Service
public class StudentHomeWorkServiceImpl implements StudentHomeWorkService {

    private static final Logger logger = LoggerFactory.getLogger(StudentHomeWorkServiceImpl.class);
    @Autowired
    private AnswerSheetMapper answerSheetMapper;
    @Autowired
    private HomeworkCollectMapper homeworkCollectMapper;
    @Autowired
    private AnswersMapper answersMapper;
    @Autowired
    private StuCollectionMapperExt stuCollectionMapperExt;

    /**
     * 获取学生作业列表
     *
     * @param params
     * @return
     */
    public BizResult<List<StudentHomeWorkStatusVo>> selectStudentHomeWorkList(StudentHomeWorkStatusParams params) {
        //获取学生作业列表
        Long now = new Date().getTime();
        List<StudentHomeWorkStatusVo> unList = new ArrayList<StudentHomeWorkStatusVo>();
        List<StudentHomeWorkStatusVo> checkList = new ArrayList<StudentHomeWorkStatusVo>();
        List<StudentHomeWorkStatusVo> selectList = homeworkCollectMapper.selectStudentHomeWorkStatusList(params);
        for (StudentHomeWorkStatusVo statusVo : selectList) {
            Integer checkNum = statusVo.getCheckedAnswerNum();      //老师批改答案数
            Integer totalNum = statusVo.getTotalAnswerCommitNum();  //上交答案数
            Date deadline = statusVo.getDeadline();
            // 状态判断
            // 1, 交作业截止时间未到，学生未交作业的情况 [学生可以去做作业] workStatus = 0
            // 2, 交作业截止时间未到，学生上交过答案了但是老师还未批改[学生可以修改作业] workStatus = 1
            // 3, 学生上交的答案老师还未批完都属于正在批改的状态 [学生不可以做任何修改] workStatus = 2
            if (deadline.getTime() > now) { //交作业截止时间未到
                if (totalNum <= 0) { //1,
                    statusVo.setWorkStatus(0);
                } else if (totalNum > 0 && checkNum <= 0) { //2,
                    statusVo.setWorkStatus(1);
                } else if (checkNum >= 0 && checkNum != totalNum) { //3,
                    statusVo.setWorkStatus(2);
                }
            } else { //3,
                statusVo.setWorkStatus(2);
            }

            if (now > deadline.getTime() && totalNum == 0) { //截至时间已经过了 && 该学生没有交作业 （该作业为已批改状态）
                checkList.add(statusVo);
            } else if (checkNum != 0 && checkNum.equals(totalNum)) {
                checkList.add(statusVo);
            } else {
                unList.add(statusVo);
            }
        }
        return BizResult.success(params.getSpecStatus() == 0 ? unList : checkList);
    }

    //作业详情信息
    public BizResult<HomeworkDetailVo> selectStudentHomeWorkDetail(HomeworkDetailParams params) {
        HomeworkDetailVo targetVo = new HomeworkDetailVo();
        HomeworkCollect homeResult = homeworkCollectMapper.selectByPrimaryKey(params.getWorkId());
        if (homeResult == null) {
            return new BizResult<HomeworkDetailVo>(BizResultCodeEnum.NOT_EXISTED);
        }
        BeanUtils.copyProperties(homeResult, targetVo);
        return BizResult.success(targetVo);
    }

    //查询学生作业答案列表显示
    public BizResult<AnswerUpdateVo> selectStudentHomeWorkAnswerList(AnswerParams params) {
        //修改，发布答案接口
        HomeworkCollect homeResult = homeworkCollectMapper.selectByPrimaryKey(params.getWorkId());
        if (homeResult == null) {
            return new BizResult<AnswerUpdateVo>(BizResultCodeEnum.NOT_EXISTED);
        }
        List<AnswerVo> selectList = answerSheetMapper.selectAnswerSheetList(params);

        List<AnswerVo> resultList = new ArrayList<AnswerVo>();
        Map<String, AnswerVo> mapAnswerVos = new HashMap<String, AnswerVo>();
        for (int i = 1; i < homeResult.getTotalNumber() + 1; i++) {
            mapAnswerVos.put(String.valueOf(i), new AnswerVo());
        }
        //
        for (AnswerVo vo : selectList) {
            String subjectId = StringUtils.trim(vo.getSubjectId());
            mapAnswerVos.put(subjectId, vo);
        }
        for (Map.Entry<String, AnswerVo> entry : mapAnswerVos.entrySet()) {
            AnswerVo vo = entry.getValue();
            resultList.add(vo);
        }
        AnswerUpdateVo updateVo = new AnswerUpdateVo();
        updateVo.setAnswerList(resultList);
        updateVo.setHomeWorkName(homeResult.getHomeworkName());
        updateVo.setWorkId(homeResult.getId());
        return BizResult.success(updateVo);
    }

    /**
     * 学生发布答案
     *
     * @param insertList
     * @return
     */
    public BizResult<String> insertStudentHomeWorkAnswer(List<UpdateAnswerParams> insertList) {
        //先根据学生编号，作业编号查询该学生改作业是否有交过作业，没有交过才能上交作业
        Integer studentId = insertList.get(0).getStudentId();
        Integer workId = insertList.get(0).getWorkId();
        HomeworkCollect hc = homeworkCollectMapper.selectByPrimaryKey(workId);
        if (hc == null) {
            return new BizResult<String>(BizResultCodeEnum.NOT_EXISTED);
        }
        AnswerParams params = new AnswerParams();
        params.setStudentId(studentId);
        params.setWorkId(workId);
        List<AnswerVo> ansList = answerSheetMapper.selectAnswerSheetList(params);
        if (CollectionUtils.isNotEmpty(ansList)) {
            logger.error(BizResultCodeEnum.DATA_EXCEPTION.getDescription() + ",该作业您已经上交过答案了不能再次上交。");
            return new BizResult<String>(BizResultCodeEnum.DATA_EXCEPTION, "该作业您已经上交过答案了不能再次上交。");
        } else {
            //执行发布答案操作
            answerSheetMapper.insertAnswerSheetBatch(insertList);
            return BizResult.success();
        }
    }

    /**
     * 学生修改答案
     *
     * @param insertUpdateList
     * @return
     */
    public BizResult<String> updateStudentHomeWorkAnswer(List<UpdateAnswerParams> insertUpdateList) {
        Date submitTime = new Date();
        //修改答案时之前有的答案修改没有的答案新增
        List<UpdateAnswerParams> updateList = new ArrayList<UpdateAnswerParams>();
        List<UpdateAnswerParams> insertList = new ArrayList<UpdateAnswerParams>();
        Map<String, AnswerVo> commitMaps = new ConcurrentHashMap<String, AnswerVo>();
        Map<String, UpdateAnswerParams> updateMaps = new ConcurrentHashMap<String, UpdateAnswerParams>();

        Integer studentId = insertUpdateList.get(0).getStudentId();
        Integer workId = insertUpdateList.get(0).getWorkId();
        HomeworkCollect hc = homeworkCollectMapper.selectByPrimaryKey(workId);
        if (hc == null) {
            return new BizResult<String>(BizResultCodeEnum.NOT_EXISTED);
        }
        //截止时间已经过了
        if (submitTime.getTime() > hc.getDeadline().getTime()) {
            logger.error(BizResultCodeEnum.DATA_EXCEPTION.getDescription() + ",该作业截止时间已经过了不能修改。");
            return new BizResult<String>(BizResultCodeEnum.DATA_EXCEPTION, "该作业截止时间已经过了不能修改。");
        }
        //查询出该学生已经上交的作业列表信息
        AnswerParams params = new AnswerParams();
        params.setStudentId(studentId);
        params.setWorkId(workId);
        List<AnswerVo> dbAnsList = answerSheetMapper.selectAnswerSheetList(params);
        if (CollectionUtils.isEmpty(dbAnsList)) {
            return new BizResult<String>(BizResultCodeEnum.DATA_EXCEPTION, "");
        }
        //学生是否还可以修改该作业答案
        for (AnswerVo ansVo : dbAnsList) {
            //获取上交过的答案集合
            String _subjectId = StringUtils.trimToEmpty(ansVo.getSubjectId());
            commitMaps.put(_subjectId, ansVo);
            if (Integer.valueOf(2).equals(ansVo.getStatus())) {
                logger.error(BizResultCodeEnum.DATA_EXCEPTION.getDescription() + "该作业答案不能被修改，原因是老师已经开始批改了该学生的答案。");
                return new BizResult<String>(BizResultCodeEnum.DATA_EXCEPTION, "该作业答案不能被修改，原因是老师已经开始批改了该学生的答案。");
            }
        }
        //已经上交的答案列表
//        Map<String, AnswerVo> commitMaps = Maps.uniqueIndex(dbAnsList, new Function<AnswerVo, String>() {
//            public String apply(AnswerVo vo) {
//                return vo.getSubjectId();
//            }
//        });
        //要修改的答案列表
        updateMaps = Maps.uniqueIndex(insertUpdateList, new Function<UpdateAnswerParams, String>() {
            public String apply(UpdateAnswerParams params) {
                return String.valueOf(params.getSubjectId());
            }
        });
        //获得要修改，新增的答案信息
        for (Map.Entry<String, UpdateAnswerParams> entry : updateMaps.entrySet()) {
            String subjectId = entry.getKey();
            UpdateAnswerParams updateParams = entry.getValue();
            AnswerVo commitVo = commitMaps.get(subjectId);
            if (commitVo != null) { //修改的对象
                updateParams.setAnswerId(commitVo.getId());
                updateList.add(updateParams);
            } else {
                insertList.add(updateParams);
            }
        }
        if (CollectionUtils.isNotEmpty(updateList)) {
            //修改要修改的答案
            answerSheetMapper.updateAnswerSheetBatch(updateList);
        }
        if (CollectionUtils.isNotEmpty(insertList)) {
            //提交没有提交的答案
            answerSheetMapper.insertAnswerSheetBatch(insertList);
        }
        return BizResult.success();
    }

    /**
     * 学生查看老师发布作业的答案
     *
     * @param lookupParams
     * @return
     */
    public BizResult<HomeWorkAnswerLookupVo> selectHomeWorkAnswerLookup(HomeWorkAnswerLookupParams lookupParams) {
        HomeWorkAnswerLookupVo lookupVo = answersMapper.selectHomeWorkAnswerLookup(lookupParams);
        if (lookupVo == null) {
            return new BizResult<HomeWorkAnswerLookupVo>(BizResultCodeEnum.NOT_EXISTED);
        }

        Date deadline = lookupVo.getDeadlineDate();
        //截至时间
        if( deadline == null || new Date().getTime() < deadline.getTime() ){
            BizResult bizResult = new BizResult<HomeWorkAnswerLookupVo>(BizResultCodeEnum.DATA_EXCEPTION);
            bizResult.setMessage("截至时间未到，不能查看答案");
            return bizResult;
        }
        return BizResult.success(lookupVo);
    }

    public BizResult<List<AnswerCheckListResultVo>> selectHomeWorkAnswerCheckedList(HomeWorkAnswerLookupParams lookupParams) {
        AnswerParams params = new AnswerParams();
        params.setStudentId(lookupParams.getStudentId());
        params.setWorkId(lookupParams.getWorkId());

        List<AnswerVo> dbSelectList = answerSheetMapper.selectAnswerSheetList(params);
        if (CollectionUtils.isEmpty(dbSelectList)) {
            return new BizResult<List<AnswerCheckListResultVo>>(BizResultCodeEnum.NOT_EXISTED);
        }
        //查询该作业的收藏信息
        StuCollectionExample example = new StuCollectionExample();
        example.setOrderByClause("id desc limit 1");
        StuCollectionExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(lookupParams.getStudentId());
        criteria.andCollectionIdEqualTo(lookupParams.getWorkId());
        criteria.andCollectionTypesEqualTo(1).andCollectionStatusEqualTo(1);
        List<StuCollection> collList = stuCollectionMapperExt.selectByExample(example);
        String collIdString = "";
        if (CollectionUtils.isNotEmpty(collList)) {
            collIdString = collList.get(0).getSubCollectionIds();
        }
        List<AnswerCheckListResultVo> resultList = new ArrayList<AnswerCheckListResultVo>();
        if (CollectionUtils.isNotEmpty(dbSelectList)) {
            for (AnswerVo vo : dbSelectList) {
                String ansID = String.valueOf(vo.getId());
                AnswerCheckListResultVo resultVo = new AnswerCheckListResultVo();
                BeanUtils.copyProperties(vo, resultVo);
                //该答案是否被收藏
                resultVo.setCollectionStatus(collIdString.contains(ansID) ? 1 : 0);
                resultList.add(resultVo);
            }
        }
        return BizResult.success(resultList);
    }

    /**
     * @param detailParams
     * @return
     */
    public BizResult<AnswerCheckDetailResultVo> selectHomeWorkAnswerCheckedDetail(AnswerCheckDetailParams detailParams) {
        AnswerCheckDetailResultVo detailResultVo = answerSheetMapper.selectStudentAnswerDetailById(detailParams);
        if (detailResultVo == null) {
            return new BizResult<AnswerCheckDetailResultVo>(BizResultCodeEnum.NOT_EXISTED);
        }
        return BizResult.success(detailResultVo);
    }


    /**
     * 学生作业未发布答案列表
     */
    public List<HomeworkCollect> selectYetUpload(Integer studentId) {
        // 作业集表中作业集IdList
        List<Integer> hwIdList = homeworkCollectMapper.selectHomeworkIdList(studentId);
        // 答题表中作业集IdList
        List<Integer> awhwIdList = answerSheetMapper.selectHomeworkIdList(studentId);

        List<HomeworkCollect> homeworkCollectList = new ArrayList<HomeworkCollect>();
        if (hwIdList != null && awhwIdList != null) {
            hwIdList.removeAll(awhwIdList);
            String strList = listToString(hwIdList);
            homeworkCollectList = homeworkCollectMapper.selectYetUpload(strList);
        } else if (hwIdList != null && awhwIdList == null) {
            String strList = listToString(hwIdList);
            homeworkCollectList = homeworkCollectMapper.selectYetUpload(strList);
        } else {
            return null;
        }
        return homeworkCollectList;
    }

    //把list转化成以‘，’割开的字符串
    public String listToString(List<Integer> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (Integer string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }
}