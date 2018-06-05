package com.fly.service;

import com.fly.bean.security.SystemUser;
import com.fly.dao.SystemUserMapper;
import com.fly.util.SystemMethodLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @SystemMethodLog(describe = "用户登录")
    public SystemUser selectUserByNameAndPassword(String name, String password) {

        return systemUserMapper.selectUserByNamePassword(name,password);
    }
}
