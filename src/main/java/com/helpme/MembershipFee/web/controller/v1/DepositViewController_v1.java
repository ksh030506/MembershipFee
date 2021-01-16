package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
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

@RequestMapping("/api/v1")
@RestController
public class DepositViewController_v1 {

    @Autowired
    private DepositViewService depositViewService;

    @Autowired
    private DepositRepository depositRepository;

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
