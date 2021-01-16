package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFeeRepository;
import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MemberShipFeeViewService {

    @Autowired
    private MemberShipFeeRepository memberShipFeeRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public Page<MemberShipFeeReturn> findMemberSipFeepage(HttpServletRequest req, final Pageable pageable) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        Page<MemberShipFeeReturn> memberShipFeeListRe = memberShipFeeRepository.findAllBy(pageable);
        if(memberShipFeeListRe == null) throw new Exception("데이터가 없습니다.");
        return memberShipFeeListRe;
    }
}
