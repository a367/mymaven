package com.fly.view;

import com.fly.service.SystemModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/module",name = "模块系统")
public class ModuleController {

    @Autowired
    SystemModuleService systemModuleService;

    @PostMapping(value = "/query",name="查询模块")
    public Object queryByModuleId(@RequestParam("modules")List<Integer> modules){
//        return systemModuleService.queryModuleByModulesID(modules);
        System.out.println(modules);
        return systemModuleService.queryModuleByModule(modules);

    }
}
