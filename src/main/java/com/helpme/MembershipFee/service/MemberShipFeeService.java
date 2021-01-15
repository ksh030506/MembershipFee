package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFeeRepository;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class MemberShipFeeService {

    @Autowired
    private MemberShipFeeRepository memberShipFeeRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public Long save(MemberShipFeeSaveRequestDto memberShipFeeSaveRequestDto, HttpServletRequest req){
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //AdminIdx 저장을 위한 관리자 불러오기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        MemberShipFee memberShipFee = MemberShipFee.builder()
                .purposeofuse(memberShipFeeSaveRequestDto.getPurposeofuse())
                .membershipfee(memberShipFeeSaveRequestDto.getMembershipfee())
                .dataofuse(memberShipFeeSaveRequestDto.getDateofuse())
                .administratorMember(administratorMember)
                .build();
        jwtUtil.validateToken(token, administratorMember);
        return memberShipFeeRepository.save(memberShipFee).getIdx_Membership_Fee();
    }

}
