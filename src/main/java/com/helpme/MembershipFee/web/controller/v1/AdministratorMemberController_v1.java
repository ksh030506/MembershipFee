package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.service.administratorMember.AdministratorMemberService;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AdministratorMemberController_v1 {

    @Autowired
    private AdministratorMemberService administratorMemberService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CookieUtil cookieUtil;

    //회원가입 엔트포인트
    @ResponseBody
    @RequestMapping(value = "/signup", method = {RequestMethod.GET, RequestMethod.POST})
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

    //로그인 엔드포인트
    @ResponseBody
    @PostMapping("/signin")
    public Map<String, String> loginUser(@RequestBody AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto, HttpServletRequest req, HttpServletResponse res) throws Exception {
        //Json으로 보내기 위해 사용
        Map<String, String> map = new HashMap<>();
        try {
            if(cookieUtil.getCookieValue(req, "accessToken") != null){
                throw new Exception("이미 로그인하였습니다");
            } else {
                final AdministratorMember member = administratorMemberService.findByEmail(administratorMemberLoginRequestDto);
                //사용자 토큰 발급 (UserEmail)
                final String token = jwtUtil.generateToken(member);
                //Token과 사용자 이메일을 쿠키에 저장
                Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
                res.addCookie(accessToken);

                map.put("msg", "로그인 성공");
                map.put("email", member.getEmail());
                map.put("Authorization",token);
            }
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }

    //로그아웃 엔드포인트
    @ResponseBody
    @GetMapping("/logout")
    public Map<String, String> logout(HttpServletResponse res){
        Map<String, String> map = new HashMap<>();
        //Value를 null로 처리.
        Cookie myCookie = new Cookie("accessToken", null);
        myCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
        myCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
        res.addCookie(myCookie);
        map.put("msg", "로그아웃 완료");
        return map;
    }
}
