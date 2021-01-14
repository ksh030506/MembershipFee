package com.helpme.MembershipFee.web.controller;

import com.google.gson.JsonObject;
import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.common.RedisUtil;
import com.helpme.MembershipFee.domain.members.Member;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.MemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
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
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private RedisUtil redisUtil;


    @PostMapping("/signup")
    public String signUpUser(@RequestBody MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        JsonObject obj = new JsonObject();
        try {
            memberService.save(memberSaveRequestDto);
            obj.addProperty("msg", "회원가입 성공");
        } catch (Exception e){
            System.out.println(e);
            obj.addProperty("msg", String.valueOf(e));
        }
        return obj.toString();
    }

    @PostMapping("/signin")
    public String loginUser(@RequestBody MemberLoginRequestDto memberLoginRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        JsonObject obj = new JsonObject();
        try {
            final Member member = memberService.findByEmail(memberLoginRequestDto);
            final String token = jwtUtil.generateToken(member);
            final  String refreshJwt = jwtUtil.generateRefreshToken(member);

            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
            redisUtil.setDataExpire(refreshJwt, member.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

            res.addCookie(accessToken);
            res.addCookie(refreshToken);

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
