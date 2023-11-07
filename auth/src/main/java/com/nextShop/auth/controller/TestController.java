package com.nextShop.auth.controller;

import com.nextShop.auth.models.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/pro")
    public BaseResponse<String> test() {
        return BaseResponse.success("Working...");
    }
}
