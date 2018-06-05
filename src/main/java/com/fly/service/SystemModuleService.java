package com.fly.service;

import com.fly.bean.security.SystemModule;
import com.fly.dao.SystemModuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemModuleService {

    @Autowired
    SystemModuleMapper systemModuleMapper;

    public List<Integer> queryUserModuleByRolesID(List<Integer> user_rolesID) {
        return  systemModuleMapper.queryUserModuleByRolesID(user_rolesID);
    }

    private void queryChildrenModule(List<SystemModule> modules){

        if(modules!=null&& modules.size()>0){
            for(SystemModule module  : modules){
                List<SystemModule> children=this.queryModuleByFatherID(module.getModuleid());
                if(children!=null){
                    module.setChildrens(children);
                    queryChildrenModule(children);
                }else{
                    return;
                }
            }
        }

    }

    private List<SystemModule> queryModuleByFatherID(Integer moduleid) {
        return systemModuleMapper.queryModuleByFatherID(moduleid);
    }

    public Object queryModuleByModulesID(List<Integer> modules) {
        List<SystemModule> module= systemModuleMapper.queryModuleByModulesID(0,modules);
        queryChildrenModule(module);
        return module;
    }

    public Object queryModuleByModule(List<Integer> modules) {
        return systemModuleMapper.queryModuleByModule(modules);
    }
}
