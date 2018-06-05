package com.fly.dao;

import com.fly.bean.business.MyStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyStudentMapper {

    List<MyStudent> selectStudent();

    int deleteByPrimaryKey(Integer id);

    int insert(MyStudent record);

    int insertSelective(MyStudent record);

    MyStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyStudent record);

    int updateByPrimaryKey(MyStudent record);
}