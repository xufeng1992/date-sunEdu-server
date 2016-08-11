package com.cn.mogo.sunEdu.core.common;/**
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
import com.cn.mogo.sunEdu.core.model.vo.PushInfoVo;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Push
 *极光推送API
 * @author xufeng
 * @date 2016/7/12 0012
 */
public class Push {

    protected static final Logger LOG = LoggerFactory.getLogger(Push.class);

    // demo App defined in resources/jpush-api.conf
    private static final String appKey ="c8cbd64f8ff80768581acb25";
    private static final String masterSecret = "b2eaa53711dca8f9ee90f6ac";

    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";


    public static void sendPush(String pushMessage,List<String> stuTelList) {

        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);

        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();
        PushPayload payload = buildPushObject_all_alias_alert(pushMessage,stuTelList);

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

//    public static PushPayload buildPushObject_all_alias_alert(String pushMessage,List<String> stuTelList) {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.alias(stuTelList))
//                .setNotification(Notification.alert(pushMessage))
//                .build();
//    }
    public static PushPayload buildPushObject_all_alias_alert(String pushMessage,List<String> stuTelList) {
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

}
