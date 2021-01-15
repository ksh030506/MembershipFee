package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.service.AdministratorMemberService;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2")
public class AdministratorMemberController_v2 {

    @Autowired
    private AdministratorMemberService administratorMemberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    @RequestMapping(value = "/signup", method = {RequestMethod.GET, RequestMethod.POST})
    public RedirectView signUpUser(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, @RequestParam(value = "name") String name,
                                   HttpServletResponse res) throws Exception {
        AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto = AdministratorMemberSaveRequestDto.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
        administratorMemberService.save(administratorMemberSaveRequestDto);
        return new RedirectView("/main");
    }

    @ResponseBody
    @PostMapping("/signin")
    public Map<String, String> loginUser(@RequestBody AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        //Json으로 보내기 위해 사용
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
