package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import com.helpme.MembershipFee.web.dto.DepositViewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Transactional(readOnly = true)
    public List<Deposit> findAll(HttpServletRequest req) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        List<Deposit> depositList = depositRepository.findAll();
        if(depositList == null) throw new Exception("데이터가 없습니다.");
        return depositList;
    }

    @Transactional
    public Integer findSumPrice(HttpServletRequest req) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        Integer priceSum = depositRepository.findSUMprice();
        if(priceSum == null) throw new Exception("데이터가 없습니다.");
        return priceSum;
    }


    @Transactional
    public List<Deposit> findName(DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        List<Deposit> depositList = depositRepository.findBySavename(depositFindByNameDto.getName());
        if(depositList.isEmpty()) throw new Exception("데이터가 없습니다.");
        return depositList;
    }
}
