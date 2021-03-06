package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.memberShipFee.MemberShipFeeService;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class MemberShipFeeController_v1 {

    @Autowired
    private MemberShipFeeService memberShipFeeService;

    @Autowired
    private CookieUtil cookieUtil;

    //회비 사용 내역 저장 엔트포인트
    @ResponseBody
    @PostMapping("/membershipadd")
    public Map<String, String> UserAdd(HttpServletRequest req, @RequestBody MemberShipFeeSaveRequestDto memberShipFeeSaveRequestDto) throws Exception {
        cookieUtil.getCookie(req, "accessToken");
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

    //날짜 조회 엔트포인트
    @ResponseBody
    @PostMapping("/memberdate")
    public List<MemberShipFeeReturn> findByCreateDateBetween(HttpServletRequest req, @RequestParam("start") String start,
                                                             @RequestParam("end") String end) throws Exception {
        cookieUtil.getCookie(req, "accessToken");
        return memberShipFeeService.findByCreateDateBetween(req, start, end);
    }

}
