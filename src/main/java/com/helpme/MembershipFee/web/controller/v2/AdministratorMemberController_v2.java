package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.service.AdministratorMemberService;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v2")
public class AdministratorMemberController_v2 {

    @Autowired
    private AdministratorMemberService administratorMemberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @RequestMapping(value = "/signup", method = {RequestMethod.GET, RequestMethod.POST})
    public String signUpUser(final AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        administratorMemberService.save(administratorMemberSaveRequestDto);
        return "main";
    }

    @RequestMapping(value = "/signin", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginUser(final AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto, HttpServletResponse res) throws Exception {
        final AdministratorMember member = administratorMemberService.findByEmail(administratorMemberLoginRequestDto);
        //사용자 토큰 발급 (UserEmail)
        final String token = jwtUtil.generateToken(member);
        //Token과 사용자 이메일을 쿠키에 저장
        Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
        res.addCookie(accessToken);
        Cookie UserEmail = cookieUtil.createCookie("UserEmail", member.getEmail());
        res.addCookie(UserEmail);
        return "redirect:/main";
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginUser(HttpServletResponse res) {
        //value를 null로 처리.
        Cookie[] myCookie = {new Cookie("UserEmail", null), new Cookie("accessToken", null)};
        for(Cookie cookie : myCookie){
            cookie.setMaxAge(0);
            cookie.setPath("/");
            res.addCookie(cookie);
        }
        return "redirect:/main";
    }
}
