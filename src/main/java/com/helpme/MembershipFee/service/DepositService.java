package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public Long save(DepositSaveRequestDto depositSaveRequestDto, HttpServletRequest req) throws Exception {
        final String token = req.getHeader("userEmail");
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        findByName(depositSaveRequestDto.getSavename());
        return depositRepository.save(depositSaveRequestDto.toEntity()).getIdx_Deposit();
    }

    //이름 검색
    public Boolean findByName(String name) throws Exception {
        Member member = memberRepository.findByMembername(name);
        if(member == null){
            throw new Exception("이름이 없습니다");
        }
        return true;
    }

    //입금 내역 날짜 조회


}