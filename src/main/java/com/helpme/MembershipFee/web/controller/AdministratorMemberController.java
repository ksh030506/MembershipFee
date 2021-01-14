package com.helpme.MembershipFee.web.controller;

import com.google.gson.JsonObject;
import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.common.RedisUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.service.AdministratorMemberService;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1")
@RestController
public class AdministratorMemberController {

    @Autowired
    private AdministratorMemberService administratorMemberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/signup")
    public String signUpUser(@RequestBody AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        JsonObject obj = new JsonObject();
        try {
            administratorMemberService.save(administratorMemberSaveRequestDto);
            obj.addProperty("msg", "회원가입 성공");
        } catch (Exception e){
            System.out.println(e);
            obj.addProperty("msg", String.valueOf(e));
        }
        return obj.toString();
    }

    @PostMapping("/signin")
    public String loginUser(@RequestBody AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        JsonObject obj = new JsonObject();
        try {
            final AdministratorMember member = administratorMemberService.findByEmail(administratorMemberLoginRequestDto);
            final String token = jwtUtil.generateToken(member);
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            res.addCookie(accessToken);

            obj.addProperty("msg", "로그인 성공");
            obj.addProperty("email", member.getEmail());
            obj.addProperty("token", token);
        } catch (Exception e){
            System.out.println(e);
            obj.addProperty("msg", String.valueOf(e));
        }
        return obj.toString();
    }
}
