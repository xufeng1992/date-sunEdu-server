package com.cn.mogo.sunEdu.App.controller;

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.core.model.params.BasicParams;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by FE on 2016/6/21.
 */
public abstract class BasicController {

    protected BaseJSONResponse verifyToken(String token) {
        BaseJSONResponse res = new BaseJSONResponse();
        if (StringUtils.isBlank(token)) {
            res.setSuccess(StatusConstant.FALSE);
            res.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            res.setMessage(StatusConstant.TOKEN_ERR_MSG);
        } else {
            res.setSuccess(true);
        }
        return res;
    }

    protected BaseJSONResponse verifyTokenParams(BasicParams params) {
        BaseJSONResponse res = this.verifyToken(params.getToken());
        if (!res.isSuccess()) {
            return res;
        }
        if (!params.verifyParams()) {
            res.setStatusCode(StatusConstant.RETURN_FAILED);
            res.setSuccess(StatusConstant.FALSE);
            res.setMessage(params.getMsg());
        }
        return res;
    }


}
