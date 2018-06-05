package com.fly.dao;

import com.fly.bean.security.PermissionTb;

import java.util.List;

public interface SystemMapper {
    int deleteByPrimaryKey(Integer permissionid);

    int insert(System record);

    int insertSelective(System record);

    System selectByPrimaryKey(Integer permissionid);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);

    List<String> queryAll();

//    Integer batchInsert(@Param("preList") List<PermissionTb> perList);
    Integer batchInsert(List<PermissionTb> list);

    List<String> queryPermissionByUserID(Integer userid);
}