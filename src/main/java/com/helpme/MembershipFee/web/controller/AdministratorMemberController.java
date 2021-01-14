package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.service.AdministratorMemberService;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class AdministratorMemberController {

    @Autowired
    private AdministratorMemberService administratorMemberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @ResponseBody
    @PostMapping("/signup")
    public Map<String, String> signUpUser(@RequestBody AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        //Json으로 보내기 위해 사용
        Map<String, String> map = new HashMap<>();
        try {
            administratorMemberService.save(administratorMemberSaveRequestDto);
            map.put("msg", "회원가입 성공");
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }

    @ResponseBody
    @PostMapping("/signin")
    public Map<String, String> loginUser(@RequestBody AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        Map<String, String> map = new HashMap<>();
        try {
            final AdministratorMember member = administratorMemberService.findByEmail(administratorMemberLoginRequestDto);
            //사용자 토큰 발급 (UserEmail)
            final String token = jwtUtil.generateToken(member);
            //Token과 사용자 이메일을 쿠키에 저장
            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
            res.addCookie(accessToken);

            //사용자 이메일 저장
            //Cookie UserEmail = cookieUtil.createCookie("UserEmail", member.getEmail());
            //res.addCookie(UserEmail);

            map.put("msg", "로그인 성공");
            map.put("email", member.getEmail());
            map.put("token", token);
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }
}
