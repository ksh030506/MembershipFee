package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v2")
public class MemberController_v2 {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/useradd", method = {RequestMethod.GET, RequestMethod.POST})
    public String UserAdd(final MemberSaveRequestDto memberSaveRequestDto, HttpServletResponse res, HttpServletRequest req) throws Exception {
        memberService.save(req, res, memberSaveRequestDto);
        return "main";
    }
}
