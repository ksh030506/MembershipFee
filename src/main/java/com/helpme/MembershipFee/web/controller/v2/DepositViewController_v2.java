package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.service.DepositViewService;
import com.helpme.MembershipFee.service.MemberService;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class DepositViewController_v2 {

    @Autowired
    private DepositViewService depositViewService;

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @GetMapping("/depositlist")
    public Page<Deposit> findAll(HttpServletRequest req, final Pageable pageable) throws Exception {
        return depositViewService.findAllPage(req, pageable);
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
