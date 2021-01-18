package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.MemberShipFeeService;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class MemberShipFeeController_v1 {

    @Autowired
    private MemberShipFeeService memberShipFeeService;

    //회비 사용 내역 저장 엔트포인트
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

    //deposit 날짜 조회 엔트포인트
    @ResponseBody
    @PostMapping("/memberdate")
    public List<MemberShipFeeReturn> findByCreateDateBetween(HttpServletRequest req, @RequestParam("start") String start, @RequestParam("end") String end) throws Exception {
        return memberShipFeeService.findByCreateDateBetween(req, start, end);
    }

}
