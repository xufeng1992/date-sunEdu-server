package com.cn.mogo.sunEdu.App.json.model;/**
 * Created by Administrator on 2016/6/22 0022.
 */

/**
 * ReturnBean
 *
 * @author xufeng
 * @date 2016/6/22 0022
 */
public class ReturnBean {

    //保存路径
    private String savePath;
    //返回值
    private int ret;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }
}
