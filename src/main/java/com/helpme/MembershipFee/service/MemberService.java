package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.domain.member.apireturn.nameReturn;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public Long save(HttpServletRequest req, HttpServletResponse res, MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        //AdminIdx 저장을 위한 관리자 불러오기
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        Member member = Member.builder()
                .membername(memberSaveRequestDto.getMembername())
                .birth(memberSaveRequestDto.getBirth())
                .administratorMember(administratorMember)
                .build();
        CheckName(memberSaveRequestDto.getMembername());
        jwtUtil.validateToken(token, administratorMember);
        return memberRepository.save(member).getIdx_Member();
    }

    //이름 중복 검사
    public Boolean CheckName(String name) throws Exception {
        Member member = memberRepository.findByMembername(name);
        if(member == null) {
            return true;
        }
        throw new Exception("이름 중복");
    }

    @Transactional
    public List<nameReturn> GetUserName(HttpServletRequest req){
        final String token = jwtUtil.GetTokenByHeader(req);
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        jwtUtil.validateToken(token, administratorMember);
        List<nameReturn> member = memberRepository.findAllBy();
        return member;
    }
}
