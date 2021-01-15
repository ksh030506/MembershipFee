package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.service.DepositViewService;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.DepositViewResponse;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class DepositViewController {

    @Autowired
    private DepositViewService depositViewService;

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @GetMapping("/depositlist")
    public List<Deposit> findAll(){
        return depositViewService.findAll();
    }

    @ResponseBody
    @GetMapping("/sumprice")
    public Map<String, Integer> findPrice(){
        Map<String, Integer> map = new HashMap<>();
        Integer sum = depositViewService.findSumPrice();
        map.put("sum", sum);
        return map;
    }


}
