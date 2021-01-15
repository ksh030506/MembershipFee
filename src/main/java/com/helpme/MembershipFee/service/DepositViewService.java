package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.web.dto.DepositViewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepositViewService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public List<Deposit> findAll(){
        return depositRepository.findAll();
    }

    @Transactional
    public Integer findSumPrice(){
        return depositRepository.findSUMprice();
    }



}
