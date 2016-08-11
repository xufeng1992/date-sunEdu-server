package com.cn.mogo.sunEdu.App.controller;/**
 * Created by Administrator on 2016/7/25 0025.
 */

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ErrorReportController
 *
 * @author xufeng
 * @date 2016/7/25 0025
 */
@RestController
@RequestMapping(value = "api/app")
public class ErrorReportController extends HttpServlet {

        private static final String EMAIL_HOST = "smtp.qq.com";  //QQ邮箱的STMP服务器
        private static final int EMAIL_PORT = 25;//QQ邮箱的STMP端口号
        private static final String EMAIL_HOST_USER="2983156351@qq.com";//你的QQ用户名
        private static final String EMAIL_HOST_PASSWORD="fdrgablusmajddid"; //你的QQ密码

//        @RequestMapping(value = "/errorReport",method = RequestMethod.POST)
//        public static void simpleEmail(@RequestParam(value="context",required=false) String context){
//            JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
//            // 设定mail server
//            senderImpl.setHost(EMAIL_HOST);
//            senderImpl.setPort(EMAIL_PORT);
//
//            // 建立邮件消息
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            // 设置收件人，寄件人 用数组发送多个邮件
//            String[] array = new String[] {"1812047715@qq.com","479791834@qq.com"};
//            mailMessage.setTo(array);
////		mailMessage.setTo("1812047715@qq.com");
//            mailMessage.setFrom("2983156351@qq.com");
//            mailMessage.setSubject(" 异常Bug ");
//            mailMessage.setText(context);
//            senderImpl.setUsername(EMAIL_HOST_USER); // 根据自己的情况,设置username
//            senderImpl.setPassword(EMAIL_HOST_PASSWORD); // 根据自己的情况, 设置password
//            Properties prop = new Properties();
//            prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
//            prop.put("mail.smtp.timeout", "25000");
//            prop.put("mail.smtp.starttls.enable", "true");
//            senderImpl.setJavaMailProperties(prop);
//            // 发送邮件
//            senderImpl.send(mailMessage);
//            System.out.println(" 邮件发送成功.. ");
//        }

    @RequestMapping(value = "/saveErrReport",method = RequestMethod.POST)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("收到客户端发来的错误日志");

        File file = new File(new File("/home/tomcat/upload"),"" + System.currentTimeMillis());

        file.getParentFile().mkdirs();
            InputStream is = req.getInputStream();
            FileOutputStream fos = new FileOutputStream(file);
       {
            byte[] buf = new byte[1024 * 8];
            int len=0;
            while((len = is.read(buf)) != -1	){
                fos.write(buf, 0, len);
            }
            resp.getWriter().write("ok");
        }


    }

}
