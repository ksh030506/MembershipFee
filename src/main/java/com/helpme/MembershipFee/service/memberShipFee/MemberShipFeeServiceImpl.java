package com.helpme.MembershipFee.service.memberShipFee;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFeeRepository;
import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MemberShipFeeServiceImpl implements MemberShipFeeService {

    @Autowired
    private MemberShipFeeRepository memberShipFeeRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    @Override
    public Long save(MemberShipFeeSaveRequestDto memberShipFeeSaveRequestDto, HttpServletRequest req) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //AdminIdx 저장을 위한 관리자 불러오기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        MemberShipFee memberShipFee = MemberShipFee.builder()
                .purposeofuse(memberShipFeeSaveRequestDto.getPurposeofuse())
                .membershipfee(memberShipFeeSaveRequestDto.getMembershipfee())
                .dateofuse(memberShipFeeSaveRequestDto.getDateofuse())
                .administratorMember(administratorMember)
                .build();
        jwtUtil.validateToken(token, administratorMember);
        BalanceCheck(memberShipFeeSaveRequestDto.getMembershipfee());
        return memberShipFeeRepository.save(memberShipFee).getIdx_Membership_Fee();
    }

    //회비 잔액 검사
    public Boolean BalanceCheck(int price) throws Exception {
        Integer price_sum = depositRepository.findSUMprice();
        if(price_sum == null) throw new Exception("입금한 데이터가 없습니다.");
        if(price_sum < price) throw new Exception("입금 금액 보다 사용 금액이 더 많습니다.");
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<MemberShipFeeReturn> findByCreateDateBetween(HttpServletRequest req, String start, String end){
        final String token = req.getHeader("userEmail");
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        return memberShipFeeRepository.findAllDateBetween(start, end);
    }
}
