package com.fly.service;

import com.fly.bean.business.SystemLogMessage;
import com.fly.dao.SystemLogMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemLogMessageService {

    @Autowired
    private SystemLogMessageMapper mapper;

    public void insert(SystemLogMessage systemLogMessage){
        mapper.insertSelective(systemLogMessage);
    }
}
