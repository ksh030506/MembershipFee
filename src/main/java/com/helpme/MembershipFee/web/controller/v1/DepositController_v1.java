package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.Deposit_IsPay;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositDateReturn;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.domain.member.apireturn.nameReturn;
import com.helpme.MembershipFee.service.DepositService;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.DepositFindSumGroupByMemberNameDto;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DepositController_v1 {

    @Autowired
    private DepositService depositService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/adddeposit")
    public Map<String, String> AddDeposit(@RequestBody DepositSaveRequestDto depositSaveRequestDto, HttpServletRequest req){
        //Json으로 보내기 위해 사용
        Map<String, String> map = new HashMap<>();
        try {
            depositService.save(depositSaveRequestDto, req);
            map.put("msg", "입금 저장 성공");
        } catch (Exception e){
            String err = String.valueOf(e);
            map.put("error", err);
        }
        return map;
    }

    @ResponseBody
    @GetMapping("/pullusername")
    public List<nameReturn> PullUserName(HttpServletRequest req){
        List<nameReturn> members = memberService.GetUserName(req);
        return members;
    }

    @ResponseBody
    @GetMapping("/pricemember")
    public List<String> findPriceGroupMember(HttpServletRequest req){
        return depositService.GroupMemberPrice(req);
    }


    @ResponseBody
    @GetMapping("/ispay")
    public List<DepositReturn> findByIsPay(HttpServletRequest req) {
        return depositService.findByIsPay(Deposit_IsPay.NO_PAY, req);
    }


    //deposit 날짜 조회
    @ResponseBody
    @PostMapping("/createdate")
    public List<Object> findByCreateDateBetween(HttpServletRequest req, @RequestParam("start")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate start, @RequestParam("end")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate end){
        return depositService.findByCreateDateBetween(req, start, end);
    }
}
