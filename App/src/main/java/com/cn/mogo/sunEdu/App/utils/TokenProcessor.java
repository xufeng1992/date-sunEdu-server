package com.cn.mogo.sunEdu.App.utils;/**
 * Created by Administrator on 2016/6/7 0007.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * TokenProcessor
 *
 * @author xufeng
 * @date 2016/6/7 0007
 */
public class TokenProcessor {

    private static TokenProcessor instance = new TokenProcessor();

    private long previous;

    protected TokenProcessor() {
    }

    public static TokenProcessor getInstance() {
        return instance;
    }

    public synchronized String generateToken(String msg, boolean timeChange) {
        try {

            long current = System.currentTimeMillis();
            if (current == previous)
                current++;
            previous = current;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            if (timeChange) {
                // byte now[] = (current+"").toString().getBytes();
                byte now[] = (new Long(current)).toString().getBytes();
                md.update(now);
            }
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String toHex(byte buffer[]) {
        StringBuffer sb = new StringBuffer(buffer.length * 2);
        for (int i = 0; i < buffer.length; i++) {
            sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
            sb.append(Character.forDigit(buffer[i] & 15, 16));
        }

        return sb.toString();
    }
}
