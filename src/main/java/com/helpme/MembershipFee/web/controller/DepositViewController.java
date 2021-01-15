package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.service.DepositViewService;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import com.helpme.MembershipFee.web.dto.DepositViewResponse;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public List<Deposit> findAll(HttpServletRequest req) throws Exception {
        return depositViewService.findAll(req);
    }

    @ResponseBody
    @GetMapping("/sumprice")
    public Map<String, Integer> findPrice(HttpServletRequest req) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        Integer sum = depositViewService.findSumPrice(req);
        map.put("sum", sum);
        return map;
    }

    @ResponseBody
    @PostMapping("/findusername")
    public List<Deposit> findBySavename(@RequestBody DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception {
        List<Deposit> deposits = depositViewService.findName(depositFindByNameDto, req);
        return deposits;
    }
}
