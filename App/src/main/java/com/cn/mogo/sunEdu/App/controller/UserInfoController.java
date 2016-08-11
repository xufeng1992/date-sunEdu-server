package com.cn.mogo.sunEdu.App.controller;/**
 * Created by Administrator on 2016/6/14 0014.
 */

import com.cn.mogo.sunEdu.App.constant.PathConstant;
import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.json.model.StudentBean;
import com.cn.mogo.sunEdu.App.json.model.UserBean;
import com.cn.mogo.sunEdu.App.utils.PropertiesUtil;
import com.cn.mogo.sunEdu.App.utils.SubjectEnum;
import com.cn.mogo.sunEdu.core.common.BCrypt;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.service.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * UserInfoController
 *
 * @author xufeng
 * @date 2016/6/14 0014
 */
@RequestMapping(value="api/app")
@RestController
public class UserInfoController {

    @Autowired
    protected RedisTemplate<String,String> redisTemplate;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private HomeworkCollectService homeworkCollectService;
    @Autowired
    private AnswersService answersService;
    @Autowired
    private StuGroupService stuGroupService;
    @Autowired
    private StudentService studentService;
    /**
     * 我的
     * @param request
     * @return
     */
    @RequestMapping(value="/my",method = RequestMethod.POST )
    public BaseJSONResponse<UserBean> myInfo(@RequestBody BaseJSONRequest<String> request) throws IOException {
        BaseJSONResponse<UserBean> response = new BaseJSONResponse<UserBean>();
        String token = request.getToken();
        Teacher teacherInfo = new Teacher();

        int teacherId = 0;
        if(token == StatusConstant.STRING_NULL || token == StatusConstant.STRING_ENPTY) {
            response.setData(null);
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else{
            Teacher teacher = new Teacher();
            teacher = getUserBytoken(token);
            if(teacher==null) {
                response.setMessage("用户信息获取失败，请重新登入");
                response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
                response.setSuccess(StatusConstant.FALSE);
            }else {
                teacherId = teacher.getTeacherId();
                teacherInfo = teacherService.findUserById(teacherId);
                Integer subjectId = teacherInfo.getSubject();
//                SubjectEnum subjectEnum1 = new SubjectEnum();
                SubjectEnum subjectEnum = SubjectEnum.getSubjecName(subjectId);
                UserBean userBean = new UserBean();
                if(subjectEnum!= null ) {
                    userBean = new UserBean(teacherId,subjectId,subjectEnum.getSubjectName(),teacherInfo.getName());
                } else {
                    userBean = new UserBean(teacherId,subjectId,"",teacherInfo.getName());
                }
                response.setData(userBean);
                response.setMessage("信息获取成功");
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
            }
        }
        return response;
    }

    /**
     * 修改个人信息(根据手机号码更新)
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public BaseJSONResponse<String> updateUserInfo(@RequestBody BaseJSONRequest<Teacher> request) throws IOException {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setData(null);
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else{
            Teacher user = new Teacher();
            user = getUserBytoken(token);
            Teacher teacher = request.getBusiness();
            teacher.setTelNo(user.getTelNo());
            teacherService.updateUserByTelNo(teacher);
            response.setData(StatusConstant.STRING_NULL);
            response.setMessage("更新成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;

    }

    /**
     * 修改密码
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/updatePassword",method = RequestMethod.POST)
    public BaseJSONResponse<String> updatePassword(@RequestBody BaseJSONRequest<UserBean> request) throws IOException {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token== StatusConstant.STRING_NULL) {
            response.setData(null);
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            UserBean user = request.getBusiness();
            String oldPassword = user.getPassword();
            String newPassword = user.getNewPassword();
            Teacher teacher = new Teacher();
            teacher = getUserBytoken(token);
            Teacher terInfo = new Teacher();
            terInfo = teacherService.findUserByTelNo(teacher.getTelNo());
            if (terInfo != null && BCrypt.checkpw(oldPassword, terInfo.getPassword())) {
                Teacher updatePassword = new Teacher();
                updatePassword.setTelNo(teacher.getTelNo());
                //密码加密
                String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                updatePassword.setPassword(hashed);
                teacherService.updateUserByTelNo(updatePassword);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
                response.setMessage("密码更新成功");
            } else {
                response.setSuccess(StatusConstant.FALSE);
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
                response.setMessage("密码更新失败");
            }

        }
        return response;

    }

    /**
     * 取得科目列表
     * @param request
     * @return
     */
    @RequestMapping(value="/subjectList",method = RequestMethod.POST)
    private BaseJSONResponse< HashMap> getSubjectList(@RequestBody BaseJSONRequest<String> request) {
        BaseJSONResponse< HashMap> response = new BaseJSONResponse< HashMap>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setData(null);
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            SubjectEnum[] allSubject =  SubjectEnum.values();
            HashMap<Integer,String> hashSub = new HashMap<Integer, String>();
            for (SubjectEnum subject : allSubject){
                hashSub.put(subject.getSubject(),subject.getSubjectName());
            }
            response.setData(hashSub);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
            response.setMessage("科目列表");
        }
        return response;
    }

    /**
     * 修改教师科目
     * @param request
     * @return
     */
    @RequestMapping(value="/updateSubject", method = RequestMethod.POST)
    private BaseJSONResponse<String> updateSubject(@RequestBody BaseJSONRequest<Teacher> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            Teacher teacher = request.getBusiness();
            int ret = teacherService.updateSubject(teacher);
            if(ret ==1) {
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
                response.setMessage("科目更新成功");
            } else {
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
                response.setMessage("科目更新失败");
            }
        }
        return response;
    }

    /**
     * 未上传答案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/yetUpload",method = RequestMethod.POST)
    private BaseJSONResponse<List<HomeworkCollect>> getList(@RequestBody BaseJSONRequest<Teacher> request) {
        BaseJSONResponse<List<HomeworkCollect>> response = new BaseJSONResponse<List<HomeworkCollect>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            Teacher teacher = request.getBusiness();
            List<HomeworkCollect> hisHomeworkCollBean= homeworkCollectService.yetUpload(teacher);
            response.setData(hisHomeworkCollBean);
            response.setMessage("未上传答案列表获取成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
        }
        return response;
    }

    /**
     *上传答案
     * @param
     * @return
     */
    @RequestMapping(value="/uploadAnswer", method = RequestMethod.POST)
    private BaseJSONResponse<String> uploadAnswer(@RequestParam(value = "token", required = true) String token,
                                                  @RequestParam(value = "answerImg",required = true) MultipartFile[] answerImg,
                                                  @RequestParam("teacherId") Integer teacherId,
                                                  @RequestParam("hwcId") Integer hwcId) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        //token判断
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            String base_gallery = PathConstant.ANSEWE_IMAGE_BASE_PATH;
            //保存路径
            String phoPaths = "";
            int imgLen = answerImg.length;
            if(imgLen > 9 ) {
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
                response.setMessage("上传图片超出9张");
            } else {
                for (int i = 0; i < imgLen; i++) {
                    MultipartFile file = answerImg[i];
                    if (file != null && !file.isEmpty()) {
                        // 获取图片的文件名
                        String fileName = file.getOriginalFilename();
                        // 获取图片的扩展名
                        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                        // 新的图片文件名 = 获取时间戳+"."图片扩展名
                        String newFileName = teacherId + "_"
                                + UUID.randomUUID().toString().replaceAll("-", "")
                                + "." + extensionName;
                        saveFile(base_gallery, newFileName, file);
                        //路径
                        String phoPath = base_gallery + File.separator
                                + newFileName;
                        //保存路径
                        phoPaths = phoPaths +";"+ phoPath;
                    }
                }
                Answers answers = new Answers();
                //保存图片路径
                answers.setAnswersUrl(phoPaths);
                //作业集Id
                answers.setHwcId(hwcId);
                //老师Id
                answers.setTeacherId(teacherId);
                //状态（0:未公布；1：公布；2:删除）
                byte status =1;
                answers.setStatus(status);
                //数据库保存答案信息
                int rec = answersService.uploadImage(answers);
                if (rec == 1) {
                    response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                    response.setSuccess(StatusConstant.SUCCESS);
                    response.setMessage("答案上传成功");
                } else{
                    response.setStatusCode(StatusConstant.RETURN_FAILED);
                    response.setSuccess(StatusConstant.FALSE);
                    response.setMessage("答案上传失败");
                }
            }
        }
        return response;
    }

    /**
     * 完善个人信息
     * @param request
     * @return
     */
    @RequestMapping(value="/improveInfo",method = RequestMethod.POST)
    public BaseJSONResponse<String> improveInfo(@RequestBody BaseJSONRequest<Teacher> request) throws IOException {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        //token判断
        if (token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setSuccess(StatusConstant.FALSE);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            Teacher teacher = getUserBytoken(token);
            if (teacher != null) {
                Teacher te = request.getBusiness();
                Teacher upteacher = new Teacher();
                upteacher.setTeacherId(teacher.getTeacherId());
                upteacher.setName(te.getName());
                upteacher.setSubject(te.getSubject());
                //完善信息
                Integer result = teacherService.updateSubject(upteacher);
                if (result ==1 ) {
                    response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                    response.setSuccess(StatusConstant.SUCCESS);
                    response.setMessage("个人信息完善成功");
                } else{
                    response.setStatusCode(StatusConstant.RETURN_FAILED);
                    response.setSuccess(StatusConstant.FALSE);
                    response.setMessage("个人信息完善失败");
                }
            }
        }
        return response;
    }

    /**
     * 我的消息
     * @param request
     * @return
     */
    @RequestMapping(value="/myClassMsg", method = RequestMethod.POST)
    public BaseJSONResponse<List<Combine>> getClassMsg(@RequestBody BaseJSONRequest<Teacher> request) {

        BaseJSONResponse<List<Combine>> response = new BaseJSONResponse<List<Combine>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Teacher teacher = request.getBusiness();

            List<Combine> Combine = stuGroupService.getMyClassMsg(teacher.getTeacherId());
            response.setData(Combine);
            response.setMessage("我的消息取得成功");
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }

    /**
     * 待发布列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/announced",method = RequestMethod.POST)
    public BaseJSONResponse<List<HomeworkCollect>> announced(@RequestBody BaseJSONRequest<Teacher> request){
        BaseJSONResponse<List<HomeworkCollect>> response = new BaseJSONResponse<List<HomeworkCollect>>();
        String token = request.getToken();
        List<HomeworkCollect> HomeworkColList = new ArrayList<HomeworkCollect>();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Teacher teacher = request.getBusiness();
            if(teacher!=null && teacher.getTeacherId()>0) {
                HomeworkColList = homeworkCollectService.getAnnounced(teacher);
            } else {
                response.setMessage("参数不正确");
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
            }
            response.setMessage("待发布列表取得成功");
            response.setData(HomeworkColList);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);

        }
        return response;
    }
    /**
     * 立刻发布
     * @param request
     * @return
     */
    @RequestMapping(value = "/ImmePublish",method = RequestMethod.POST)
    public BaseJSONResponse<String> immePublish(@RequestBody BaseJSONRequest<HomeworkCollect> request){
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            HomeworkCollect homeworkCollect = request.getBusiness();
            //作业状态0：正常 1：删除
            Byte status =  homeworkCollect.getStatus();
            if(homeworkCollect!=null && homeworkCollect.getId()>0 && status!=null ) {
                //立刻发布时
                if (status == 0) {
                    int result = homeworkCollectService.immePublish(homeworkCollect);
                    response.setMessage("立刻发布成功");
                    //删除待发布作业
                } else {
                    int result = homeworkCollectService.delPublish(homeworkCollect);
                    response.setMessage("待发布作业删除成功");
                }

            } else {
                response.setMessage("参数不正确");
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
            }
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);

        }
        return response;
    }
    /**
     *
     * @param baseURL 路径
     * @param newFileName 新的图片文件名
     * @param filedata 文件
     */
    private void saveFile(String baseURL, String newFileName, MultipartFile filedata) {
        // TODO Auto-generated method stub
        // 根据配置文件获取服务器图片存放路径
        String picDir = "";
        // 这里封装了读取配置文件的方法 配置文件中有图片的存放地址和获取地址
        Properties properties = PropertiesUtil.getDefaultProperties("app_server.properties");
        picDir = properties.getProperty("savePicUrl");
        String saveFilePath = picDir + File.separator + baseURL;
		/* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(saveFilePath
                    + File.separator + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得本地数据
     * @param token
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public Teacher getUserBytoken(String token) throws JsonParseException, JsonMappingException, IOException {
        Teacher teacher = null;
        if (token != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJSON = redisTemplate.opsForValue().get(token);
            if (userJSON != null && !StatusConstant.STRING_ENPTY.equals(userJSON)) {
                teacher = objectMapper.readValue(userJSON, Teacher.class);
            }
        }
        return teacher;
    }


}
