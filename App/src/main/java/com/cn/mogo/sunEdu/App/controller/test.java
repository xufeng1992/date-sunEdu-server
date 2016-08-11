package com.cn.mogo.sunEdu.App.controller; /**
 * Created by Administrator on 2016/7/12 0012.
 */

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.cn.mogo.sunEdu.core.common.Push;
import com.cn.mogo.sunEdu.core.model.vo.PushInfoVo;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Push
 *
 * @author xufeng
 * @date 2016/7/12 0012
 */
public class test {

    protected static final Logger LOG = LoggerFactory.getLogger(Push.class);

    // demo App defined in resources/jpush-api.conf
    private static final String appKey ="c8cbd64f8ff80768581acb25";
    private static final String masterSecret = "b2eaa53711dca8f9ee90f6ac";

    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";

    public static void main(String[] args) {
//        testSendPushWithCustomConfig();
//        testSendIosAlert();
//        String pushMessage ="测试推送";
//        List<String> stuTelList = new ArrayList<String>();
//        stuTelList.add("15321768790");
//        testSendPush(pushMessage, stuTelList);
        Date date = new Date();
        long time = date.getTime();
        System.out.println(time);
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH;mm");
        String hah = form.format(date);
        String xixi =form.format(time);
        System.out.println(xixi);
        System.out.println(hah);
    }


    public static PushPayload buildPushObject_all_alias_alert(String pushMessage,List<String> stuTelList) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(stuTelList))
                .setNotification(Notification.alert(pushMessage))
                .build();
    }

    public static PushPayload buildPushObject_android_and_io(String pushMessage,List<String> stuTelList) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(stuTelList))
                .setNotification(Notification.newBuilder()
                        .setAlert(pushMessage)
                        .addPlatformNotification(IosNotification.newBuilder()
                                .incrBadge(1).build())
                        .build())
                .build();
    }

//    public static PushPayload buildPushObject_all_all_alert() {

    public static void testSendPush(String pushMessage,List<String> stuTelList) {
        // HttpProxy proxy = new HttpProxy("localhost", 3128);
        // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);

        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();
        PushPayload payload = buildPushObject_android_and_io(pushMessage, stuTelList);

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }
//        return PushPayload.alertAll(ALERT);
//    }


//    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android())
//                .setAudience(Audience.tag("tag1"))
//                .setNotification(Notification.android(ALERT, TITLE, null))
//                .build();
//    }

}
