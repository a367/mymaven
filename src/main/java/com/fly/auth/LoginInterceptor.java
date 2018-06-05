package com.fly.auth;

import com.fly.auth.exception.NOLoginException;
import com.fly.auth.exception.NOPermissionException;
import com.fly.auth.token.JSON_WEB_TOKEN;
import com.fly.auth.token.Token;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String url = request.getServletPath();

        if(url.matches(SystemUtils.STATIC_NO_PERMISSION_PATH)){
            return true;
        }

        String token = request.getParameter("TOKEN");

        Token tokenUtil = new Token();

        List<String> userPermission = tokenUtil.unCreateToken(JSON_WEB_TOKEN.class,token).getPermissions();

        if(userPermission == null ){
            throw new NOLoginException("未登录");
        }

        if(handler instanceof HandlerMethod){
            String permissionURL = SystemUtils.getMethodOfPermission((HandlerMethod) handler);
            if(!userPermission.contains(permissionURL)){
                throw new NOPermissionException("你没有该资源的权限！！！");
            }

            return true;
        }

        return false    ;
    }
}
