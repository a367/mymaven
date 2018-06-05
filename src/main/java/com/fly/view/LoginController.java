package com.fly.view;


import com.fly.auth.SystemUtils;
import com.fly.auth.exception.NOLoginException;
import com.fly.auth.token.JSON_WEB_TOKEN;
import com.fly.auth.token.Token;
import com.fly.bean.security.Page;
import com.fly.bean.security.SystemUser;
import com.fly.service.SystemModuleService;
import com.fly.service.SystemRoleService;
import com.fly.service.SystemService;
import com.fly.service.SystemUserService;
import com.fly.util.LoginResult;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/login",name = "登录系统")
public class LoginController {

    @Autowired
    HttpSession session;

    @Autowired
    SystemService systemService;

    @Autowired
    SystemRoleService systemRoleService;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    SystemModuleService systemModuleService;

    @Autowired
    Token token;

    @PostMapping("/confirm")
    public Page login(String name,String password) throws NOLoginException, UnsupportedEncodingException {
        SystemUser systemUser=systemUserService.selectUserByNameAndPassword(name,password);
        if (systemUser==null){
            return  new Page("用户名或密码错误",1);
        }
        //得到用户所有权限
        List<String> permissions = systemService.queryPermissionByUserID(systemUser.getUserid());

        //得到用户的所有角色ID
        List<Integer> user_rolesID=systemRoleService.queryUserRolesByUserId(systemUser.getUserid());

        List<Integer> user_moduleID = null;

        if(user_rolesID!=null){
            user_moduleID=systemModuleService.queryUserModuleByRolesID(user_rolesID);

        }

        JSON_WEB_TOKEN json_web_token = new JSON_WEB_TOKEN(systemUser.getUserid(),permissions);

        LoginResult loginResult = new LoginResult(systemUser.getUserid(),systemUser.getUsername(),
                user_rolesID,permissions,user_moduleID,token.createToken(json_web_token,12*60*60*1000));



        return new Page(0,loginResult);
    }

    @GetMapping("/user")
    public String hello(){
        List<String> permissions = new ArrayList<>();
        permissions.add("file:select");
        permissions.add("system:update");
        session.setAttribute(SystemUtils.PERMISSION_NAME,permissions);
        return "登录";
    }

    @GetMapping("/token")
    public String token() throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = token.createToken(systemService.queryAll(),1111111111);
        List list=token.unCreateToken(List.class,json);
        return  gson.toJson(list);
    }
}
