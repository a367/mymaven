package com.fly.service;

import com.fly.dao.MyStudentMapper;

import com.fly.bean.security.Page;
import com.fly.util.SystemMethodLog;
import com.fly.util.SystemProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuBus {

    @Autowired
    private MyStudentMapper mapper;

    @SystemMethodLog(describe = SystemProperties.SELECT_STUDENT_S)
    public Page selectStudent(Integer page, Integer size){
        PageHelper.startPage(page,size);
        PageInfo pageInfo = new PageInfo(mapper.selectStudent());
        return new Page(pageInfo,0);
    }

    @SystemMethodLog(describe = SystemProperties.DELETE_STUDENT_S)
    public boolean deleteStudent(Integer id){

        int count=mapper.deleteByPrimaryKey(id);
        if(count >0){
            return true;
        }
        return false;
    }
}
