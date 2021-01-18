package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class DepositViewService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    //입금 조회 + 페이징 처리
    @Transactional(readOnly = true)
    public Page<DepositReturn> findAllPage(HttpServletRequest req, final Pageable pageable) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        Page<DepositReturn> depositList = depositRepository.findAllBy(pageable);
        if(depositList == null) throw new Exception("데이터가 없습니다.");
        return depositList;
    }

    //입금 총액 조회
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


    //이름 조회
    @Transactional
    public List<DepositReturn> findName(DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //토큰 검사를 위한 관리자 계정 찾기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        List<DepositReturn> depositList = depositRepository.findBySavename(depositFindByNameDto.getName());
        if(depositList.isEmpty()) throw new Exception("데이터가 없습니다.");
        return depositList;
    }

}
