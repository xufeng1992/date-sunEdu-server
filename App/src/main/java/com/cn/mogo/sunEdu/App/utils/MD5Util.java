package com.cn.mogo.sunEdu.App.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by FE on 2016/6/28.
 */
public class MD5Util {

    public static String md5(String oriStr) {
        if (StringUtils.isBlank(oriStr)) {
            return null;
        }
        byte[] btInput = oriStr.getBytes();
        MessageDigest mdInst = null;
        try {
            mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            StringBuilder sb = new StringBuilder();
            String str;
            for (int i = 0; i < md.length; ++i) {
                str = Integer.toHexString(md[i] & 0xFF);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
