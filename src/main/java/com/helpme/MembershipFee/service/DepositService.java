package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.deposit.Deposit_IsPay;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.web.dto.DepositFindSumGroupByMemberNameDto;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        //Idx_member 넣기
        Member member = memberRepository.findByMembername(depositSaveRequestDto.getSavename());
        Deposit deposit = Deposit.builder()
                .savename(depositSaveRequestDto.getSavename())
                .price(depositSaveRequestDto.getPrice())
                .build();
        if(depositSaveRequestDto.getDeposit_isPay()){
            deposit.setIsPay(Deposit_IsPay.IS_PAY);
        } else {
            deposit.setIsPay(Deposit_IsPay.NO_PAY);
        }
        return depositRepository.save(deposit).getIdx_Deposit();
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



    //사용자별 입금 내역 조회
    @Transactional
    public List<String> GroupMemberPrice(HttpServletRequest req){
        final String token = req.getHeader("userEmail");
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        return depositRepository.findSUMpriceBymember();
    }


    //미납 내역 조회
    @Transactional
    public List<Deposit> findByIsPay(Deposit_IsPay deposit_isPay, HttpServletRequest req){
        final String token = req.getHeader("userEmail");
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        return depositRepository.findByIsPay(deposit_isPay);
    }

}