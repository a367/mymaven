package com.fly.dao;

import com.fly.bean.business.SystemLogMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemLogMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemLogMessage record);

    int insertSelective(SystemLogMessage record);

    SystemLogMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemLogMessage record);

    int updateByPrimaryKeyWithBLOBs(SystemLogMessage record);

    int updateByPrimaryKey(SystemLogMessage record);
}