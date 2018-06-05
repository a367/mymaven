package com.fly.view;

import com.fly.bean.security.PermissionTb;
import com.fly.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping(value = "/system",name = "系统模块")
@RestController
public class SystemController {

    @Autowired
    private RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping;

    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/update",name = "更新系统权限")
    public Object updateSystemPermission(){
        Integer K=(Integer) this.modifySystemPermission();
        return "更新了"+K+"条权限";
    }

    private synchronized Object modifySystemPermission() {

        List<String> systemAlready = systemService.queryAll();

        List<PermissionTb> permissionList = new ArrayList<>();

        Map<RequestMappingInfo,HandlerMethod> mappingInfoHandlerMethodMap=
                    requestMappingInfoHandlerMapping.getHandlerMethods();

        Collection<HandlerMethod> handlerMethods = mappingInfoHandlerMethodMap.values();
        if(handlerMethods==null || handlerMethods.isEmpty()){
            return null;
        }
        for (HandlerMethod method:handlerMethods){
            System.out.println("chakan---"+method);
        }

        for (HandlerMethod method:handlerMethods) {
            RequestMapping methodRequestMapping=method.getMethodAnnotation(RequestMapping.class);
            if(!"".equals(methodRequestMapping.name())){
                String methodURL=methodRequestMapping.value()[0];
                RequestMapping classRequestMapping=
                        method.getBeanType().getAnnotation(RequestMapping.class);
                String module = "".equals(classRequestMapping.name())?"":classRequestMapping.name();
                String classURL = classRequestMapping.value()[0];
                String permissionURL =(classURL+":"+methodURL).replace("/","");

                if(systemAlready!=null){
                    if(systemAlready.contains(permissionURL)){
                        continue;
                    }
                }
                PermissionTb permissionTb=new PermissionTb();
                permissionTb.setPermissionvalue(permissionURL);
                permissionTb.setPermissionmodule(module);
                permissionTb.setPermissionname(methodRequestMapping.name());
                permissionList.add(permissionTb);
            }
        }
        System.out.println("---------"+permissionList);
        try {
            return  systemService.insertSystemPermission(permissionList);
        } catch (Exception e) {
            System.out.println("异常");
            return 0;
        }

    }
}
