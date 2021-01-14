package com.helpme.MembershipFee.web.controller;

import com.helpme.MembershipFee.service.DepositService;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @PostMapping("/adddeposit")
    public String AddDeposit(@RequestBody DepositSaveRequestDto depositSaveRequestDto){
        depositService.save(depositSaveRequestDto);
        return "저장 완료";
    }

}
