package com.cn.mogo.sunEdu.App.controller;

/**
 * Created by Administrator on 2016/6/21 0021.
 */

import com.cn.mogo.sunEdu.App.constant.PathConstant;
import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.json.model.ReturnBean;
import com.cn.mogo.sunEdu.App.utils.PropertiesUtil;
import com.cn.mogo.sunEdu.core.common.Push;
import com.cn.mogo.sunEdu.core.model.*;
import com.cn.mogo.sunEdu.core.service.AnswersService;
import com.cn.mogo.sunEdu.core.service.HomeworkCollectService;
import com.cn.mogo.sunEdu.core.service.PushService;
import com.cn.mogo.sunEdu.core.service.StudentService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HomeWorkController
 *
 * @author xufeng
 * @date 2016/6/21 0021
 */

@RequestMapping(value = "/api/app")
@RestController
public class HomeWorkController {

	@Autowired
	private HomeworkCollectService homeworkCollectService;
	@Autowired
	private AnswersService answersService;
	@Autowired
	private PushService pushService;
	@Autowired
	private StudentService studentService;

	/**
	 *
	 * @param token
	 * @param teacherId
	 *            老师Id
	 * @param hwName
	 *            作业名称
	 * @param groupIds
	 *            群组名称
	 * @param studentIds
	 *            学生Ids
	 * @param pubdate
	 *            发布时间
	 * @param deadline
	 *            截止时间
	 * @param totalNumber
	 *            题目总数
	 * @param totalPoints
	 *            总分数
	 * @param homeworkImg
	 *            作业图片
	 * @param answerImg
	 *            答案图片
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/publishhw", method = RequestMethod.POST)
	public BaseJSONResponse<String> publishhw(
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "teacherId", required = true) Integer teacherId,
			@RequestParam(value = "hwName", required = true) String hwName,
			@RequestParam(value = "groupIds", required = true) String groupIds,
			@RequestParam(value = "studentIds", required = true) String studentIds,
			@RequestParam(value = "pubdate", required = true) Long pubdate,
			@RequestParam(value = "deadline") Long deadline,
			@RequestParam(value = "remark") String remark,
			@RequestParam(value = "totalNumber", required = true) int totalNumber,
			@RequestParam(value = "totalPoints", required = true) int totalPoints,
			@RequestParam(value = "homeworkImg") MultipartFile[] homeworkImg,
			@RequestParam("answerImg") MultipartFile[] answerImg)
			throws ParseException {
		BaseJSONResponse<String> response = new BaseJSONResponse<String>();

		if (token == StatusConstant.STRING_ENPTY
				|| token == StatusConstant.STRING_NULL) {
			response.setSuccess(StatusConstant.FALSE);
			response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
			response.setMessage(StatusConstant.TOKEN_ERR_MSG);
		} else {
			// 班群Ids和学生ids的判断
			if ((groupIds.length()) > 0 && (studentIds.length()) > 0) {
				HomeworkCollect hwc = new HomeworkCollect();
				// 创建人(老师)Id
				hwc.setCreaterId(teacherId);
				// 班群Id
				hwc.setGroupIds(groupIds);
				// 学生Ids
				hwc.setStudentIds(studentIds);
				// 截止日期
				Date retDeadline = dataFormat(deadline);
				hwc.setDeadline(retDeadline);
				// 发布日期
				Date retPubdate = dataFormat(pubdate);
				hwc.setPubdate(retPubdate);
				// 总题数
				hwc.setTotalNumber(totalNumber);
				// 总分数
				hwc.setTotalPoints(totalPoints);
				// 作业名称
				hwc.setHomeworkName(hwName);
				// 状态 0:发布；1：删除
				byte a = 0;
				hwc.setStatus(a);
				//备注
				hwc.setRemark(remark);

				// 答案路径
				String ans_url = PathConstant.ANSEWE_IMAGE_BASE_PATH;
				// 作业路径
				String hw_url = PathConstant.HW_IMAGE_BASE_PATH;
				// 保存路径
				String phoPaths = "";
				System.out.println(answerImg.length);

				// 作业和答案上传
				if (answerImg != null && (answerImg.length) != 0) {
					ReturnBean rethw = uploadImage(homeworkImg, hw_url,
							phoPaths, teacherId);
					ReturnBean retas = uploadImage(answerImg, ans_url,
							phoPaths, teacherId);
					// 两者都超出9张
					if (rethw.getRet() == 0 || retas.getRet() == 0) {
						response.setStatusCode(StatusConstant.RETURN_FAILED);
						response.setSuccess(StatusConstant.FALSE);
						response.setMessage("上传图片超出9张");
						// 作业为null
					} else if (rethw.getRet() == 2) {
						response.setStatusCode(StatusConstant.RETURN_FAILED);
						response.setSuccess(StatusConstant.FALSE);
						response.setMessage("请选择作业图片上传");
					} else {
						hwc.setHomeworkPic(rethw.getSavePath());
						Integer hwId = homeworkCollectService
								.publishHomeWork(hwc);

						Answers answers = new Answers();
						answers.setHwcId(hwc.getId());
						answers.setTeacherId(teacherId);
						answers.setGroupIds(groupIds);
						answers.setAnswersUrl(retas.getSavePath());
						int ret = answersService.uploadImage(answers);
						response.setStatusCode(StatusConstant.RETURN_SUCCESS);
						response.setSuccess(StatusConstant.SUCCESS);
						response.setMessage("作业和答案发布成功");
						// 推送消息并保存在数据库
						insertPushMessage(studentIds, teacherId, pubdate);
					}
					// 作业上传
				} else {
					ReturnBean rethw = uploadImage(homeworkImg, hw_url,
							phoPaths, teacherId);
					if (rethw.getRet() == 0) {
						response.setStatusCode(StatusConstant.RETURN_FAILED);
						response.setSuccess(StatusConstant.FALSE);
						response.setMessage("上传图片超出9张");
					} else if (rethw.getRet() == 2) {
						response.setStatusCode(StatusConstant.RETURN_FAILED);
						response.setSuccess(StatusConstant.FALSE);
						response.setMessage("请选择作业图片上传");
					} else {
						hwc.setHomeworkPic(rethw.getSavePath());
						Integer hwId = homeworkCollectService
								.publishHomeWork(hwc);
						response.setStatusCode(StatusConstant.RETURN_SUCCESS);
						response.setSuccess(StatusConstant.SUCCESS);
						response.setMessage("作业发布成功");
						// 推送消息并保存在数据库
						insertPushMessage(studentIds, teacherId, pubdate);
					}
				}
			} else if ((groupIds.length()) == 0 || (studentIds.length()) == 0) {
				response.setStatusCode(StatusConstant.RETURN_FAILED);
				response.setSuccess(StatusConstant.FALSE);
				response.setMessage("请选择班群或者学生");
			}

		}
		return response;
	}

	// 推送消息保存在数据库
	public void insertPushMessage(String studentIds, int teacherId, long pubdate) {
		// 获取学生信息
		List<Student> studentList = studentService.selectStuByIds(studentIds);
		List<String> stuTelList = new ArrayList<String>();
		List<PushDo> pushDoList = new ArrayList<PushDo>();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String pubDate = form.format(pubdate * 1000);
		String pushMessage = pubDate + "时您将收到一份作业，请注意查看！";
		for (Student student : studentList) {
			PushDo pushDo = new PushDo();
			int studentId = student.getStudentId();
			String telNo = student.getTelNo();
			if (StringUtils.isNoneBlank(telNo)) {
				stuTelList.add(telNo);
			}
			pushDo.setPushPeople(teacherId);
			pushDo.setReceiver(studentId);
			pushDo.setPushContent(pushMessage);
			pushDoList.add(pushDo);
		}
		// 推送消息保存在数据库
		int ret = pushService.insertPushMessage(pushDoList);
		// 推送
		if (CollectionUtils.isNotEmpty(stuTelList)) {
			Push.sendPush(pushMessage, stuTelList);
		}
	}

	/**
	 * 时间戳转化成Date
	 * 
	 * @param getDate
	 * @return
	 */
	public Date dataFormat(Long getDate) throws ParseException {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String d = form.format(getDate * 1000);
		Date result = form.parse(d);

		return result;
	}

	/**
	 * 上传图片
	 * 
	 * @param files
	 *            文件
	 * @param base_gallery
	 *            路径
	 * @param phoPaths
	 *            保存路径
	 * @param teacherId
	 *            老师Id
	 * @return ret:0：上传图片超出了9张，1：正常上传;2:上传文件为null
	 */
	private ReturnBean uploadImage(MultipartFile[] files, String base_gallery,
			String phoPaths, Integer teacherId) {
		// 返回值
		int ret = 0;
		ReturnBean returnBean = new ReturnBean();
		// 超出9张
		if (files != null && (files.length) > 9) {
			returnBean.setRet(0);
			// 正常
		} else if (files != null && (files.length) <= 9) {
			for (int i = 0; i < (files.length); i++) {
				MultipartFile file = files[i];
				if (file != null && !file.isEmpty()) {
					// 获取图片的文件名
					String fileName = file.getOriginalFilename();
					// 获取图片的扩展名
					String extensionName = fileName.substring(fileName
							.lastIndexOf(".") + 1);
					// 新的图片文件名 = 获取时间戳+"."图片扩展名
					String newFileName = teacherId + "_"
							+ UUID.randomUUID().toString().replaceAll("-", "")
							+ "." + extensionName;
					saveFile(base_gallery, newFileName, file);
					// 路径
					// String phoPath = base_gallery + "/" + newFileName;
					String phoPath = base_gallery + File.separator
							+ newFileName;
					// 保存路径
					phoPaths = phoPaths + ";" + phoPath;
				}
			}
			returnBean.setRet(1);
			returnBean.setSavePath(phoPaths);
			// 为空
		} else {
			returnBean.setRet(2);
		}
		return returnBean;
	}

	/**
	 * 文件上传
	 * 
	 * @param baseURL
	 *            路径
	 * @param newFileName
	 *            新的图片文件名
	 * @param filedata
	 *            文件
	 */
	private void saveFile(String baseURL, String newFileName,
			MultipartFile filedata) {
		// TODO Auto-generated method stub
		// 根据配置文件获取服务器图片存放路径
		String picDir = "";
		// 这里封装了读取配置文件的方法 配置文件中有图片的存放地址和获取地址
		Properties properties = PropertiesUtil
				.getDefaultProperties("app_server.properties");
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

	public boolean foo(String param) {
		// 经常用这种方法来判断对象是不是空
		if (param == null && "".equals(param)) {
			// Some Code
			return false;
		}
		return true;
		// Do someting
	}

}
