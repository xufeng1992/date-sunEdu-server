package com.cn.mogo.sunEdu.App.utils;/**
 * Created by Administrator on 2016/6/23 0023.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CommUtil
 *
 * @author xufeng
 * @date 2016/6/23 0023
 */
public class CommUtil {

    //判断字符串中是否为数字
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
