package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/two")
@RefreshScope
public class TwoController {

    @Value("${ttt.ip}")
    public String ip;

    @RequestMapping("/test")
    public String test(){
        return "two的ip为："+this.ip;
    }
}
