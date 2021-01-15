package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController_v2 {
    @Autowired
    private MemberService memberService;

    @ResponseBody
    @PostMapping("/useradd")
    public Map<String, String> UserAdd(HttpServletRequest req, HttpServletResponse res, @RequestBody MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        //Json으로 보내기 위해 사용
        Map<String, String> map = new HashMap<>();
        try {
            memberService.save(req, res, memberSaveRequestDto);
            map.put("msg", "유저 저장 성공");
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }
}
