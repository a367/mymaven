package com.fly.dao;

import com.fly.bean.business.MyScore;


public interface MyScoreMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MyScore record);

    int insertSelective(MyScore record);

    MyScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyScore record);

    int updateByPrimaryKey(MyScore record);
}