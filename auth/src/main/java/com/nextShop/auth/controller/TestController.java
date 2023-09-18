package com.nextShop.auth.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class TestController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/test")
    public String test() {
        return "App name: " + appName;
    }

}
