package com.ssm.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class IndexController {

    /**
     * 默认访问页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "login";
    }
}
