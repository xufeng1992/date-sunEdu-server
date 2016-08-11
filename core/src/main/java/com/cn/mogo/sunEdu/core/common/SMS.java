package com.cn.mogo.sunEdu.core.common;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2015/11/13 0013.
 * 短信接口
 */
public class SMS {
    protected static final Logger LOG = LoggerFactory.getLogger(SMS.class);
    public static final String Uid = "135";//企业id
    public static final String Account = "hzmgkj";//发送用户帐号
    public static final String Password = "mg123456";//密码
    public static final String SMS_SEND_URI = "http://sms3.pro-group.com.cn:8888/sms.aspx";

    public static boolean sendMessage(String telNumber, String smsText) throws HttpException, IOException {
        PostMethod post = new PostMethod(SMS_SEND_URI);
        NameValuePair[] data = {
                new NameValuePair("action", "send"),//发送任务命令 设置为固定
                new NameValuePair("userid", Uid),//企业id
                new NameValuePair("account", Account),//发送用户帐号
                new NameValuePair("password", Password),//发送帐号密码
                new NameValuePair("mobile", telNumber),//全部被叫号码
                new NameValuePair("content", smsText),//发送内容
                new NameValuePair("sendTime", null),//定时发送时间
                new NameValuePair("checkcontent", 0 + ""),//是否检查内容包含非法关键字
                new NameValuePair("taskName", null),//任务名称
                new NameValuePair("countnumber", 1 + ""),//号码总数量
                new NameValuePair("mobilenumber", 1 + ""),//手机号码数量
                new NameValuePair("telephonenumber", 0 + ""),//小灵通或座机号码数
        };
        String result = executeMethod(post, data);
        String[] mm = result.split("<returnstatus>");
        String[] ss = mm[1].split("</returnstatus>");
        LOG.info("发送短信数量：" + result + "，手机号：" + telNumber + "信息：" + smsText);
        System.out.println(result);
        post.releaseConnection();
        if ("Success".equals(ss[0])) return true;
        return false;
    }

    private static String executeMethod(PostMethod post, NameValuePair[] data) throws HttpException, IOException {
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        post.setRequestBody(data);
        HttpClient client = new HttpClient();
        client.executeMethod(post);
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        byte[] b = post.getResponseBody();
        return new String(b, "utf-8");
    }

}
