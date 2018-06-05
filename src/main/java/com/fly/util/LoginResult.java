package com.fly.util;

import java.util.List;

//处理登录结果集
public class LoginResult {

    private Integer user_id;
    private String user_name;
    private List<Integer> user_roles_id;
    private List<Integer> user_module_id;
    private List<String> user_permission;
    private String token;

    public LoginResult(Integer userid, String username, List<Integer> user_rolesID, List<String> permissions,List<Integer> user_modules, String token) {

        super();
        this.user_id=userid;
        this.user_name=username;
        this.user_roles_id=user_rolesID;
        this.user_permission=permissions;
        this.user_module_id=user_modules;
        this.token=token;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<Integer> getUser_roles_id() {
        return user_roles_id;
    }

    public void setUser_roles_id(List<Integer> user_roles_id) {
        this.user_roles_id = user_roles_id;
    }

    public List<Integer> getUser_module_id() {
        return user_module_id;
    }

    public void setUser_module_id(List<Integer> user_module_id) {
        this.user_module_id = user_module_id;
    }

    public List<String> getUser_permission() {
        return user_permission;
    }

    public void setUser_permission(List<String> user_permission) {
        this.user_permission = user_permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}