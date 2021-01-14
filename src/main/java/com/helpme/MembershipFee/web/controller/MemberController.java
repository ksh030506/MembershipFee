package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1")
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ResponseBody
    @PostMapping("/useradd")
    public void UserAdd(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        memberService.save(req, res, memberSaveRequestDto);
    }
}
