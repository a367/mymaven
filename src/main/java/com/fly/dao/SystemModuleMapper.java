package com.fly.dao;

import com.fly.bean.security.SystemModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemModuleMapper {
    int deleteByPrimaryKey(Integer moduleid);

    int insert(SystemModule record);

    int insertSelective(SystemModule record);

    SystemModule selectByPrimaryKey(Integer moduleid);

    int updateByPrimaryKeySelective(SystemModule record);

    int updateByPrimaryKey(SystemModule record);

    List<Integer> queryUserModuleByRolesID(List<Integer> list);

    List<SystemModule> queryModuleByModulesID(@Param("father") Integer father, @Param("module") List<Integer> modules);

    List<SystemModule> queryModuleByFatherID(Integer moduleid);

    List<SystemModule> queryModuleByModule(List<Integer> list);
}