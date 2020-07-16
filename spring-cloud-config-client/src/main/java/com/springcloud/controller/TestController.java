package com.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class TestController {

    @Value("${ttt.ip}")
    private String ip;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "client的ip地址为：" + this.ip;
    }
}
