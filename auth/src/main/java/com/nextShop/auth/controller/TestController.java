package com.nextShop.auth.controller;

import com.nextShop.auth.model.base.BaseResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public BaseResponse<String> test() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.success(userDetails.getUsername());
    }


    @GetMapping("/test/no")
    public BaseResponse<String> test2() {
        return BaseResponse.success("Please login for more detailed information...");
    }
}
