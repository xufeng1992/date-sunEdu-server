package com.cn.mogo.sunEdu.App.utils;/**
 * Created by Administrator on 2016/6/7 0007.
 */

import org.codehaus.plexus.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PhoneUtil
 *
 * @author xufeng
 * @date 2016/6/7 0007
 */
public class PhoneUtil {

    public static boolean phoneNumber(String number) {
        String rgx = "^((13[0-9])|(18[0,0-9])|(17[0,6-8])|(15[0-9])|(14[5,7]))\\d{8}$";
        return isCorrect(rgx, number);
    }

    private static boolean isCorrect(String rgx, String res) {
        Pattern p = Pattern.compile(rgx);
        Matcher m = p.matcher(res);
        return m.matches();
    }


    //    public static boolean telCheck(String tel){
//       String mm = tel.replace("-","").trim();
//        switch (mm.length()){
//            case 11:return phoneNumber(mm);
//            case 13:return tel13(mm);
//            case 14:return tel14(mm);
//            default:return false;
//        }
//    }
    public static String telCheckIsFriend(String tel){
        if (StringUtils.isBlank(tel))return null;
        String mm = tel.replace("-","").trim();
        if (mm.length()==13)
            mm=tel13(mm);
        if (mm.length()==14)
            mm=tel14(mm);
        if (phoneNumber(mm))
            return mm;
        return null;
    }

    public static Map<String,String> telCheckIsFriend(String[] tel){
        if (tel==null||tel.length==0)return null;
        Map<String,String>  TEL = new HashMap<String,String>();
        for (String m:tel){
            String mm = m.replace("-","").trim();
            if (mm.length()==13)
                mm=tel13(mm);
            if (mm.length()==14)
                mm=tel14(mm);
            if (phoneNumber(mm))
                TEL.put(m,mm);
            else TEL.put(m,"");
        }
        return TEL;
    }
    private static String tel13(String tel){
        if (tel.indexOf("86")==0)
            return tel.substring(2,tel.length());
        return "";
    }
    private static String tel14(String tel){
        if (tel.indexOf("+86")==0)
            return tel.substring(3,tel.length());
        return "";
    }


//    public static void main(String[] m){
//        String[] args = new String[7];
//        args[0] = "13777873255";
//        args[1] = "177-0705-6656";
//        args[2] = "13777873255";
//        args[3] = "13777873255";
//        args[4] = "8615757195731";
//        args[5] = "+8613777873255";
//        args[6] = "8614277873255";
//        telCheckIsFriend(args);
//    }

}