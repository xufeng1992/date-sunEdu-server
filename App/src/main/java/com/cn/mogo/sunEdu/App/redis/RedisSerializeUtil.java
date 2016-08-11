package com.cn.mogo.sunEdu.App.redis;/**
* Created by Administrator on 2016/6/7 0007.
*/


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
* RedisSerializeUtil
*
* @author xufeng
* @date 2016/6/7 0007
*/
public class RedisSerializeUtil {
    // 序列化
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 反序列化
    public static Object deSeialize(byte[] bytes) {
        ByteArrayInputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayOutputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("deserialize exception");

        }
        return null;
    }

}