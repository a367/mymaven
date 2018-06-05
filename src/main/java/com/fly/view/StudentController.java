package com.fly.view;


import com.fly.bean.security.Page;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/stu")
public class StudentController {

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String hello(String name,String pass){
        return name+":"+pass;
    }


    @RequestMapping(value="/{page}/{size}",method =RequestMethod.PUT)
    public String hello_1(@PathVariable("page") String page, @PathVariable("size") String size){
        return page+":"+size;
    }

    @RequestMapping(value="/{id}",method =RequestMethod.DELETE)
    public Integer hello_2(@PathVariable("id") Integer id){
        return id;
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public Page getPage(Page page){
        return page;
    }

    @RequestMapping(value = "/page",method = RequestMethod.PUT)
    public Page getPage_1(@RequestBody Page page){
        return page;
    }
}
