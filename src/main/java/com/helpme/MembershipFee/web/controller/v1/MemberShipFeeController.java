package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.service.MemberShipFeeService;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberShipFeeController {

    @Autowired
    private MemberShipFeeService memberShipFeeService;

    @PostMapping("/membershipadd")
    public Map<String, String> UserAdd(HttpServletRequest req, @RequestBody MemberShipFeeSaveRequestDto memberShipFeeSaveRequestDto) throws Exception {
        //Json으로 보내기 위해 사용
        Map<String, String> map = new HashMap<>();
        try {
            memberShipFeeService.save(memberShipFeeSaveRequestDto, req);
            map.put("msg", "회비 사용 내역 저장 성공");
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }

}
