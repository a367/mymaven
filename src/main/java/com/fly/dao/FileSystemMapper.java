package com.fly.dao;

import com.fly.bean.business.FileSystem;

import java.util.List;

public interface FileSystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileSystem record);

    int insertSelective(FileSystem record);

    FileSystem selectByPrimaryKey(Integer id);

    List<FileSystem> selectByPage();

    int updateByPrimaryKeySelective(FileSystem record);

    int updateByPrimaryKey(FileSystem record);

    FileSystem selectFilePathByName(String name);
}