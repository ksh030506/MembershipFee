package com.helpme.MembershipFee.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
@Controller
public class ViewController {

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/deposit")
    public String depositPage(){
        return "deposit";
    }

    @GetMapping("/depositview")
    public String depositViewPage(){
        return "depositView";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/memberadd")
    public String memberAdd(){
        return "memberAdd";
    }

    @GetMapping("/membershipfeeadd")
    public String membershipFeeAdd(){
        return "membershipFeeAdd";
    }

    @GetMapping("/membershipfeeview")
    public String membershipFeeView(){
        return "membershipFeeView";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

}
