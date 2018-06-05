package com.fly.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;

public class SystemUtils {

    public static final String STATIC_NO_PERMISSION_PATH=
    ".*/((login)|(logout)|(mvc)|(app)|(weixin)|(static)|(main)|(websocket)).*";

    public static final String PERMISSION_NAME="PERMISSION";

    public static String getMethodOfPermission(HandlerMethod handler) {
        String permissionName = handler.getMethodAnnotation(RequestMapping.class).name();
        if("".equals(permissionName)){
            return  null;
        }
        String permissionNameURL=handler.getBeanType().getAnnotation(RequestMapping.class).value()[0];
        String permissionModuleURL = handler.getBeanType().getAnnotation(RequestMapping.class).value()[0];
        return (permissionModuleURL+":"+permissionNameURL).replace("/","");
    }
}
