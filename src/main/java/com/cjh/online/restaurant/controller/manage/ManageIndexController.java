package com.cjh.online.restaurant.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageIndexController {

    @RequestMapping("/adminIndex")
    public String adminIndex(){
        return "admin/index";
    }
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "admin/login";
    }
}
