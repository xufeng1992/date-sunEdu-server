package com.cn.mogo.sunEdu.App.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {

    public static final String separator = "/";

    public static void writeFile(String filePath, String context) {
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        try {
            File downloadFile = new File(filePath);
            downloadFile.getParentFile().mkdirs();
            if (!downloadFile.exists()) {
                downloadFile.createNewFile();
            }
            outSTr = new FileOutputStream(downloadFile);
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            Buff.write(context.getBytes());
            Buff.flush();
            Buff.close();
            long end0 = System.currentTimeMillis();
            System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 豪秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Buff.close();
                outSTr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转存多个文件
     *
     * @param baseURL
     * @param srcFileData
     * @param cover
     * @return
     */
    public static List<String> saveFileMutail(String baseURL, MultipartFile[] srcFileData, boolean cover) {
        if (srcFileData == null) {
            throw new NullPointerException("fileData is null ...转存的文件为空.");
        }
        List<String> pathList = new ArrayList<String>();
        String _filePath = "";

        for (MultipartFile srcFile : srcFileData) {
            if (srcFile == null) {
                _filePath = "";
            } else {
                try {
                    byte[] bytes = srcFile.getBytes();
                    if (bytes.length == 0) {
                        continue;
                    }
                    _filePath = FileUtil.saveFile(baseURL, srcFile, cover);
                } catch (Exception exc) {
                    exc.printStackTrace();
                    _filePath = "";
                }
            }
            pathList.add(_filePath);
        }
        return pathList;
    }


    /**
     * 转存多个文件
     *
     * @param baseURL
     * @param srcFileData
     * @param cover
     * @return
     */
    public static Map<String, String> saveAnswerFile(String baseURL, MultipartFile[] srcFileData, boolean cover) {
        String _answerPreffix = "answer_";
        Map<String, String> ansPathMap = new HashMap<String, String>();
        if (srcFileData == null) {
            throw new NullPointerException("fileData is null ...转存的文件为空.");
        }
        for (MultipartFile srcFile : srcFileData) {
            String _originName = srcFile.getOriginalFilename();
            String _name = _originName.substring(0, _originName.lastIndexOf("."));
            String _subjectId = StringUtils.trim(_name.replaceAll(_answerPreffix, ""));
            if (srcFile != null) {
                try {
                    byte[] bytes = srcFile.getBytes();
                    if (bytes.length == 0) {
                        continue;
                    }
                    String _filePath = FileUtil.saveFile(baseURL, srcFile, cover);
                    ansPathMap.put(_subjectId, _filePath);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }
        return ansPathMap;
    }

    /**
     * 保存文件
     *
     * @param baseURL
     * @param cover       是否覆盖
     * @param srcFileData
     * @return
     */
    public static String saveFile(String baseURL, MultipartFile srcFileData, boolean cover) {
        //文件访问统一资源定位器 去除
        String fileURL = "";
        // 这里封装了读取配置文件的方法 配置文件中有图片的存放地址和获取地址
        Properties properties = PropertiesUtil.getDefaultProperties("app_server.properties");
        if (srcFileData == null) {
            throw new NullPointerException("fileData is null ...转存的文件为空.");
        }
        // 根据配置文件获取服务器图片存放路径
        String originName = srcFileData.getOriginalFilename();
        //文件名称后缀
        String _suffix = originName.substring(originName.lastIndexOf("."));
        String fileName = cover == true ? originName : UUID.randomUUID().toString().replaceAll("-", "") + _suffix;
        String specDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String saveFilePath = properties.getProperty("savePicUrl") + baseURL + FileUtil.separator + specDir + FileUtil.separator + fileName;
        fileURL = baseURL + FileUtil.separator + specDir + FileUtil.separator + fileName;
        /* 构建文件目录 */
        File targetFile = new File(saveFilePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            srcFileData.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }


    public static String copyFile(String baseURL, MultipartFile srcFileData, boolean cover) {
        // 根据配置文件获取服务器图片存放路径
        String fileURL = "";
        try {
            Properties properties = PropertiesUtil.getDefaultProperties("app_server.properties");
            String originName = srcFileData.getOriginalFilename();
            //文件名称后缀
            String _suffix = originName.substring(originName.lastIndexOf("."));
            String fileName = cover == true ? originName : UUID.randomUUID().toString() + _suffix;
            String specDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String saveFilePath = properties.getProperty("savePicUrl") + baseURL + FileUtil.separator + specDir + FileUtil.separator + fileName;
            fileURL = baseURL + FileUtil.separator + specDir + FileUtil.separator + fileName;
            File fileDir = new File(saveFilePath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(fileDir);
            // 写入文件
            out.write(srcFileData.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileURL;
    }

}
