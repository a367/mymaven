package com.fly.service;

import com.fly.bean.business.FileSystem;
import com.fly.bean.security.Page;
import com.fly.dao.FileSystemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service

public class FileService {

    private static final String path = "G:/Apache/path/";

    @Autowired
    private FileSystemMapper mapper;

    private static String getUploadTime(){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String time=simpleDateFormat.format(date);
        return time;
    }
    private static String getFileNewName(){

        String fileName=System.currentTimeMillis()+"";
        return fileName;

    }

    private static String getFileSuffix(String fileName){

        int index=fileName.lastIndexOf(".");
        String suffix=fileName.substring(index);
        return suffix;
    }
    public static FileSystem saveFileOnDisk(MultipartFile file) throws IOException {

        FileSystem fileSystem = new FileSystem();
        String fileOldName = file.getOriginalFilename();
        fileSystem.setOldName(fileOldName);
        String fileType = getFileSuffix(fileOldName);
        fileSystem.setType(fileType);

        String fileUploadTime = getUploadTime();
        fileSystem.setUploadDate(fileUploadTime);

        String fileNewName = getFileNewName();
        fileSystem.setNewName(fileNewName);

        String fileUploadPath =path + fileUploadTime +"/"+fileNewName+fileType;
        fileSystem.setPath(fileUploadPath);

        File target = new File(fileUploadPath);

        if(target.exists()){
            target.mkdirs();
        }
        file.transferTo(target);

        return fileSystem;
    }

    //    @SystemMethodLog(describe = "文件上传操作")
    public boolean insertFile(MultipartFile file){
        try {
            FileSystem fileSystem = saveFileOnDisk(file);
            mapper.insertSelective(fileSystem);
            return true;

        }catch (Exception e){

            return false;

        }
    }

    public Page selectFile(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);

        Page pages = new Page(new PageInfo(mapper.selectByPage()),0);

        return pages;
    }

    public FileSystem selectFilePathById(Integer id) {

        FileSystem fileSystem=mapper.selectByPrimaryKey(id);
        return fileSystem;
    }

    public String selectFilePathByName(String name){
        FileSystem fileSystem = mapper.selectFilePathByName(name);
        return  fileSystem.getPath();
    }
}
