package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.domain.members.Member;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.MemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public String signUpUser(@RequestBody MemberSaveRequestDto memberSaveRequestDto) throws Exception {
//        JsonObject obj = new JsonObject();
//        try {
//            memberService.save(memberSaveRequestDto);
//            obj.addProperty("success", "회원가입 성공");
//        } catch (Exception e){
//            System.out.println(e);
//            obj.addProperty("msg", String.valueOf(e));
//        }
//        return obj.toString();
        memberService.save(memberSaveRequestDto);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public Member loginUser(@RequestBody MemberLoginRequestDto memberLoginRequestDto) throws Exception {
        Member member = memberService.findByEmail(memberLoginRequestDto);
        return member;
    }
}
