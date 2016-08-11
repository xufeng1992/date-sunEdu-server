package com.cn.mogo.sunEdu.core.result;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 返回业务结果
 */
public class BizResult<T> extends BaseResult {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5935703301155834061L;

    /**
     * 返回结果实例。
     */
    private T resultObj;

    /**
     * 默认构造函数
     */
    private BizResult() {
    }

    /**
     * 构造函数
     *
     * @param code
     * @param codeNumber
     * @param message
     */
    public BizResult(String code, int codeNumber, String message) {
        super(code, codeNumber, message);
    }

    /**
     * 构造方法
     *
     * @param success    成功标示
     * @param resultCode 返回值code
     */
    public BizResult(boolean success, String resultCode, int codeNumber, String description) {
        super(success, resultCode, codeNumber, description);
    }

    /**
     * 构造方法
     *
     * @param resultCode
     */
    public BizResult(BizResultCodeEnum resultCode, String extDesc) {
        super(resultCode);
        if(StringUtils.isNotBlank(extDesc)){
            this.setMessage(resultCode.getDescription() + "," + extDesc);
        }
    }

    /**
     * 构造方法
     *
     * @param resultCode
     */
    public BizResult(BizResultCodeEnum resultCode) {
        super(resultCode);
    }

    /**
     * 构造方法
     *
     * @param resultCode
     */
    public BizResult(BizResultCodeEnum resultCode, boolean success) {
        super(resultCode, success);
    }

    /**
     * 构造方法
     *
     * @param resultCode
     */
    public BizResult(BizResultCodeEnum resultCode, boolean success, T resultObj) {
        super(resultCode, success);
        this.resultObj = resultObj;
    }

    /**
     * 返回默认成功对象
     *
     * @return
     */
    public static <T> BizResult<T> success() {
        return success(null);
    }

    public static <T> BizResult<T> success(T resultObj) {
        return new BizResult<T>(BizResultCodeEnum.SUCCESS, true, resultObj);
    }

    public T getResultObj() {
        return resultObj;
    }

    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
