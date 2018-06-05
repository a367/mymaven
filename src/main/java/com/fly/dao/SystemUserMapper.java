package com.fly.dao;

import com.fly.bean.security.SystemUser;
import org.apache.ibatis.annotations.Param;

public interface SystemUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);

    SystemUser selectUserByNamePassword(@Param("name") String name,@Param("password") String password);
}