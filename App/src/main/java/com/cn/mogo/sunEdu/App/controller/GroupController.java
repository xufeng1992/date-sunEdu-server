package com.cn.mogo.sunEdu.App.controller;/**
* Created by Administrator on 2016/6/13 0013.
*/

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.json.model.UserBean;
import com.cn.mogo.sunEdu.core.common.Push;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.model.params.GroupBean;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;
import com.cn.mogo.sunEdu.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
* ClassController
*
* @author xufeng
* @date 2016/6/13 0013
*/
@RequestMapping(value="/api/app")
@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private StuGroupService stuGroupService;
    @Autowired
    private HomeworkCollectService homeworkCollectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PushService pushService;

    /**
     * 创建班群
     * @param request
     * @return
     */
    @RequestMapping(value = "/createGroup",method = RequestMethod.POST)
    public BaseJSONResponse<String> createGroup(@RequestBody BaseJSONRequest<GroupBean> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        GroupBean groupBean = new GroupBean();
        String token = request.getToken();

        if (token=="" || token==StatusConstant.STRING_NULL) {
            response.setData(null);
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        }else {
            groupBean = request.getBusiness();
            Group group = new Group();
            //创建人
            group.setCreatePeople(groupBean.getCreatePeople());
            //群组名
            group.setGroupName(groupBean.getGroupName());
            //简介
            group.setAnnouncement(groupBean.getAnnouncement());
            int count = groupService.createGroup(group);
            if(count > 0) {
                response.setData(null);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setMessage("班群创建成功");
                response.setSuccess(StatusConstant.SUCCESS);

            } else{
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setMessage("班群创建失败");
                response.setSuccess(StatusConstant.FALSE);
            }
        }
        return response;
    }

    /**
     * 获取班级群列表接口
     * @param request
     * @return
     */
    @RequestMapping(value="/groupList", method = RequestMethod.POST)
    public BaseJSONResponse<List<GroupBean>> groupList(@RequestBody BaseJSONRequest<UserBean> request) {

        BaseJSONResponse<List<GroupBean>> response = new BaseJSONResponse<List<GroupBean>>();
        String token = request.getToken();
        if(token ==StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            UserBean userBean = request.getBusiness();
            int teacherId = userBean.getTeacherId();
            List<Group> groupList = groupService.getGroupList(teacherId);

            List<GroupBean> groupBeanList = new ArrayList<GroupBean>();
            if(groupList != null) {
                for(Group group : groupList) {
                    GroupBean groupBean = new GroupBean();
                    int groupId = 0;
                    groupId = group.getGroupId();
                   int sumPeople =  stuGroupService.getCount(groupId);
                    groupBean.setGroupId(group.getGroupId());
                    groupBean.setGroupName(group.getGroupName());
                    groupBean.setAnnouncement(group.getAnnouncement());
                    groupBean.setCountPeople(sumPeople);
                    groupBean.setCreatePeople(group.getCreatePeople());
                    groupBeanList.add(groupBean);
                }

            }
            response.setData(groupBeanList);
            response.setMessage("群组列表获取成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     *
     * 获取班级详情
     * @param request
     * @return
     */
    @RequestMapping(value="/getGroupDetails", method = RequestMethod.POST)
    public BaseJSONResponse<Group> getGroupDetails(@RequestBody BaseJSONRequest<GroupBean> request){
        BaseJSONResponse<Group> response = new BaseJSONResponse<Group>();
        Group group = new Group();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            GroupBean groupBean = new GroupBean();
            groupBean = request.getBusiness();
            int groupId = groupBean.getGroupId();
            group = groupService.getGroupDetails(groupId);
            response.setData(group);
            response.setMessage("班群详情获取成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);

        }
        return response;
    }

    /**
     * 获取班级成员接口
     * @param request
     * @return
     */
    @RequestMapping(value="/getStudentList", method = RequestMethod.POST)
    public BaseJSONResponse<List<Student>> getStudentList(@RequestBody BaseJSONRequest<GroupBean> request) {
        BaseJSONResponse<List<Student>> response = new BaseJSONResponse<List<Student>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            GroupBean groupBean = request.getBusiness();
            Integer groupId = groupBean.getGroupId();
            //获取班级成员
            List<Student> studentList = new ArrayList<Student>();
            studentList = stuGroupService.getAddStuIdList(groupId);
            response.setData(studentList);
            response.setMessage("班级成员获取成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     * 班群内删除学生
     * @param request
     * @return
     */
    @RequestMapping(value="/delGroupStu", method = RequestMethod.POST)
    public BaseJSONResponse<String> delGroupStu(@RequestBody BaseJSONRequest<StuGroup> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            StuGroup stuGroup = new StuGroup();
            stuGroup = request.getBusiness();
            int ret = stuGroupService.delStudent(stuGroup);
            if(ret > 0) {

                Push push = new Push();
                Group group = groupService.getGroupDetails(stuGroup.getGroupId());
                //推送消息
                String pushMessage = "你已经被移出" + group.getGroupName()+"作业班群";
                Student student = studentService.selectStuById(stuGroup.getStudentId());
                List<PushDo> pushDoList = new ArrayList<PushDo>();
                PushDo pushDo = new PushDo();
                pushDo.setPushPeople(student.getStudentId());
                //推送接收人
                pushDo.setReceiver(student.getStudentId());
                pushDo.setPushContent(pushMessage);
                pushDoList.add(pushDo);

                //推送消息保存在数据库
                int result = pushService.insertPushMessage(pushDoList);
                //推送
                List<String> stuTelList = new ArrayList<String>();
                stuTelList.add(student.getTelNo());
                Push.sendPush(pushMessage,stuTelList);
                response.setData(null);
                response.setMessage("学生删除成功");
                response.setSuccess(StatusConstant.SUCCESS);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            }else {
                response.setData(null);
                response.setMessage("学生删除失败");
                response.setSuccess(StatusConstant.FALSE);
                response.setStatusCode(StatusConstant.RETURN_FAILED);
            }
        }
        return response;
    }

    /**
     * 获取班级消息
     * @param request
     * @return
     */
    @RequestMapping(value="/classMsg", method = RequestMethod.POST)
    public BaseJSONResponse<List<Combine>> getClassMsg(@RequestBody BaseJSONRequest<Group> request) {

        BaseJSONResponse<List<Combine>> response = new BaseJSONResponse<List<Combine>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Group group = request.getBusiness();
            //群组Id
            Integer groupId = group.getGroupId();
            List<Combine> Combine = stuGroupService.getGroupClassMsg(groupId);
            response.setData(Combine);
            response.setMessage("班群消息取得成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     * 班群消息审核
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkApply",method = RequestMethod.POST)
    public BaseJSONResponse<String> checkApply(@RequestBody BaseJSONRequest<Combine> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Combine combine = request.getBusiness();
            int ret = stuGroupService.checkApply(combine);
            response.setData(null);
            response.setMessage("班群消息审核成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     * 历史作业
     * @param request
     * @return
     */
    @RequestMapping(value="/historyHomeWork", method = RequestMethod.POST)
    public BaseJSONResponse<List<HisHomeworkCollBean>> historyHomeWork(@RequestBody BaseJSONRequest<Group> request) {

        BaseJSONResponse<List<HisHomeworkCollBean>> response = new BaseJSONResponse<List<HisHomeworkCollBean>>();
        String token = request.getToken();
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Group group = request.getBusiness();
            List<HisHomeworkCollBean> hisHomeworkCollBeanList =  homeworkCollectService.getHisHomeworkCollList(group);
            response.setData(hisHomeworkCollBeanList);
            response.setMessage("历时作业取得成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            }
        return response;
    }

    /**
     * 修改班级信息 type==0时修改简介 type==1时修改群名称
     * @param request
     * @return
     */
    @RequestMapping(value="/updateGroup",method = RequestMethod.POST)
    public BaseJSONResponse<String> updateGroup(@RequestBody BaseJSONRequest<GroupBean>request) {

        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            GroupBean groupBean = request.getBusiness();
            String groupName = groupBean.getGroupName();
            String announcement = groupBean.getAnnouncement();
            Integer groupId = groupBean.getGroupId();
            Group group = new Group();
            group.setGroupName(groupName);
            group.setAnnouncement(announcement);
            group.setGroupId(groupId);
            int type= groupBean.getType();
           groupService.updateGroup(type, group);
            response.setMessage("班级信息修改成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
    return response;
    }

    /**
     * 学生端班群列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStuClass",method = RequestMethod.POST)
    public BaseJSONResponse<List<GroupBean>>getStuClass(@RequestBody BaseJSONRequest<StudentParam> request){
        BaseJSONResponse<List<GroupBean>> response = new BaseJSONResponse<List<GroupBean>>();
        String token = request.getToken();
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            StudentParam studentParam = request.getBusiness();
            //学生端班群列表
            List<GroupBean> groupBeanList = stuGroupService.getGroupBeanList(studentParam);
            response.setData(groupBeanList);
            response.setMessage("学生班级列表获取成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     * 学生端查询班级
     * @param request
     * @return
     */
    @RequestMapping(value = "/stuFindClass",method = RequestMethod.POST)
    public BaseJSONResponse<List<Group>> getGroupList(@RequestBody BaseJSONRequest<GroupBean> request) {

        BaseJSONResponse<List<Group>> response = new BaseJSONResponse<List<Group>>();
        String token = request.getToken();
        GroupBean groupBean = request.getBusiness();
        String groupName = groupBean.getGroupName();
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else if (groupName!=null && groupName!=""){
            List<Group> groupList = groupService.stuFindClass(groupName);
            response.setData(groupList);
            response.setMessage("学生查询班级成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        } else {
            response.setData(null);
        }
    return response;
    }

    /**
     * 学生添加群
     * @param request
     * @return
     */
    @RequestMapping(value = "/stuAddGroup",method = RequestMethod.POST)
    public BaseJSONResponse<String> stuAddGroup(@RequestBody BaseJSONRequest<StuGroup> request){
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        StuGroup stuGroup = request.getBusiness();

        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else{
            //申请添加班群
            Integer result = stuGroupService.applyAdd(stuGroup);
            if (result==1) {
                response.setMessage("学生申请班群成功");
                response.setSuccess(StatusConstant.SUCCESS);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            } else{
                response.setMessage("学生已经申请或者已加入班群");
                response.setSuccess(StatusConstant.FALSE);
                response.setStatusCode(StatusConstant.RETURN_FAILED);
            }

        }
        return response;
    }

    /**
     * 获取老师名字
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTeacherName",method = RequestMethod.POST)
    public BaseJSONResponse<Teacher> getTeacherName(@RequestBody BaseJSONRequest<Teacher> request) {
        BaseJSONResponse<Teacher> response = new BaseJSONResponse<Teacher>();
        String token = request.getToken();
        Teacher teacher = request.getBusiness();

        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else{
            Teacher teach = teacherService.findUserById(teacher.getTeacherId());
            if (teach==null){
                Teacher te = new Teacher();
                response.setData(te);
                response.setMessage("老师信息不存在");
                response.setSuccess(StatusConstant.SUCCESS);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            } else {
                response.setData(teach);
                response.setMessage("老师信息获取成功");
                response.setSuccess(StatusConstant.SUCCESS);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            }

        }
        return response;
    }
}
