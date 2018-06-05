package com.fly.service;


import com.fly.bean.security.PermissionTb;
import com.fly.dao.SystemMapper;
import com.fly.util.SystemMethodLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {

    @Autowired
    private SystemMapper mapper;

    @SystemMethodLog(describe = "查询系统所有权限")
    public List<String> queryAll(){

        return mapper.queryAll();
    }
    public Integer insertSystemPermission(List<PermissionTb> permissionTbList){

        return mapper.batchInsert(permissionTbList);
    }

    @SystemMethodLog(describe = "查询用户权限")
    public List<String> queryPermissionByUserID(Integer userid) {
        return mapper.queryPermissionByUserID(userid);
    }
}
