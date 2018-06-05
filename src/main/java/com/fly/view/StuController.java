package com.fly.view;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/student",name = "学生系统")
public class StuController {

    @GetMapping(value = "/{id}",name = "查询学生权限")
    public String getStu(@PathVariable("id") String id){

        return id;
    }
}
