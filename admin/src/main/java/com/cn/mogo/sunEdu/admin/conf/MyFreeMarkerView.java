package com.cn.mogo.sunEdu.admin.conf;

import com.cn.mogo.sunEdu.core.common.Constant;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by chang on 2016/6/3.
 */

public class MyFreeMarkerView extends FreeMarkerView {

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
//        String serverName = request.getServerName();
//        String contextPath = request.getContextPath();
//        int port = request.getServerPort();
//        String webUrl = serverName + ":" + port + contextPath;
        model.put("webUrl", Constant.adminAddressPrefix+Constant.adminAddress+":"+Constant.adminPort);
        super.exposeHelpers(model, request);
    }
}
