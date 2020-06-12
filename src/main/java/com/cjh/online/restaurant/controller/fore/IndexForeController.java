package com.cjh.online.restaurant.controller.fore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexForeController {

    @RequestMapping("/index")
    public String indexFore(){
        return "fore/index";
    }
}
