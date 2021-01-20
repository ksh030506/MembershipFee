package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.service.depositview.DepositViewService;
import com.helpme.MembershipFee.service.member.MemberServiceImpl;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class DepositViewController_v1 {

    @Autowired
    private DepositViewService depositViewService;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private MemberServiceImpl memberService;

    //입금 조회 엔트포인트
    @ResponseBody
    @GetMapping("/depositlist")
    public Page<DepositReturn> findAll(HttpServletRequest req, final Pageable pageable) throws Exception {
        return depositViewService.findAllPage(req, pageable);
    }

    //입금 총액 조회 엔트포인트
    @ResponseBody
    @GetMapping("/sumprice")
    public Map<String, Integer> findPrice(HttpServletRequest req) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        Integer sum = depositViewService.findSumPrice(req);
        map.put("sum", sum);
        return map;
    }

    //이름 조회 엔트포인트
    @ResponseBody
    @PostMapping("/findusername")
    public List<DepositReturn> findBySavename(@RequestBody DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception {
        return depositViewService.findName(depositFindByNameDto, req);
    }
}
