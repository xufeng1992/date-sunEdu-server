package com.cn.mogo.sunEdu.App.heapler;

import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.core.result.BizResult;

/**
 * Created by FE on 2016/7/5.
 */
public class ConvertorHelper {
    
    public static BaseJSONResponse convertorBizToResponse(BizResult bizResult){
        BaseJSONResponse res = new BaseJSONResponse();
        res.setSuccess(bizResult.isSuccess());
        res.setStatusCode(String.valueOf(bizResult.getCodeNumber()));
        res.setMessage(bizResult.getMessage());
        res.setData(bizResult.getResultObj());
        return res;
    }

}
