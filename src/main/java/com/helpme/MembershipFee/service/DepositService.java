package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public Long save(DepositSaveRequestDto depositSaveRequestDto){
        return depositRepository.save(depositSaveRequestDto.toEntity()).getIdx_Deposit();
    }

}
