package com.fly.service;

import com.fly.dao.SystemRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemRoleService {

    @Autowired
    SystemRoleMapper systemRoleMapper;

    public List<Integer> queryUserRolesByUserId(Integer userid) {
        return systemRoleMapper.queryUserRolesByUserId(userid);
    }
}
