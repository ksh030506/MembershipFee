package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.common.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController_v2 {

    @Autowired
    private CookieUtil cookieUtil;

    //메인 페이지
    @GetMapping("/main")
    public String mainPage(HttpServletRequest req, Model model){
        model.addAttribute("UserEmail", cookieUtil.getCookieValue(req, "UserEmail"));
        return "main";
    }

    //회원가입 페이지
    @RequestMapping(value="/register" , method = {RequestMethod.GET, RequestMethod.POST})
    public String register(){
        return "register";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //입금 페이지
    @GetMapping("/deposit")
    public String depositPage(){
        return "deposit";
    }

    //입금 조회 페이지
    @GetMapping("/depositview")
    public String depositViewPage(){
        return "depositView";
    }

    //멤버 추가 페이지
    @GetMapping("/memberadd")
    public String memberAdd(){
        return "memberAdd";
    }

    //회비 사용 내역 추가 페이지
    @GetMapping("/membershipfeeadd")
    public String membershipFeeAdd(){
        return "membershipFeeAdd";
    }

    //회비 사용 내역 조회 페이지
    @GetMapping("/membershipfeeview")
    public String membershipFeeView(){
        return "membershipFeeView";
    }
}
