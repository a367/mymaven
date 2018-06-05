package com.fly.view;

import com.fly.bean.business.FileSystem;
import com.fly.bean.security.Page;
import com.fly.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@CrossOrigin
@RestController
@RequestMapping(value = "/file",name = "文件系统")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/download/{id}",name = "下载权限")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("id") Integer id) throws IOException {

        FileSystem fileSystem = fileService.selectFilePathById(id);

        String downloadFilename = URLEncoder.encode(fileSystem.getOldName(),"utf-8");

        String filePath = fileSystem.getPath();

        File file = new File(filePath);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment",downloadFilename);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "/select",name = "查询文件权限")
    public Page selectFile(Integer page,Integer limit){

        Page pages=fileService.selectFile(page,limit);
        return pages;
    }



    @PostMapping(value = "/upload",name = "上传权限")
    public boolean upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return false;
        }
        return fileService.insertFile(file);

    }

    @PostMapping(value = "/uploads",name = "多文件上传权限")
    public boolean uploads(MultipartFile[] file){
        boolean flag = true;

        if(file.length==0){
            return false;
        }
        for(int i=0;i<file.length;i++){
            if(file[i].isEmpty()){
                continue;
            }
            if(!fileService.insertFile(file[i])){
                flag=false;
            }
        }
        return flag;
    }
}
