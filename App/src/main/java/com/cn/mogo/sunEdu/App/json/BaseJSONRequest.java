package com.cn.mogo.sunEdu.App.json;

/**
 * BaseJSONRequest
 * @author xufeng
 * @date 2016/6/6 0006
 */
public class BaseJSONRequest<T> extends Result {

//    private static final long serialVersionUID = 2323318762667261050L;
    private String token;
    private String signature;
    private T business;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public T getBusiness() {
        return business;
    }

    public void setBusiness(T business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return "BaseJSONRequest [token=" + token + ", signature=" + signature + ", business=" + business + "]";
    }

}
