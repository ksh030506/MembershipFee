package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.CookieUtil;
import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Transactional
    public void save(HttpServletRequest req, MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        if(validateToken(req) == true) {
            if(CheckName(memberSaveRequestDto.getMembername()) == false){
                throw new Exception("이름 중복");
            } else {
                String userEmail = getUserEmail(req);
                AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
                Member member = Member.builder()
                        .membername(memberSaveRequestDto.getMembername())
                        .birth(memberSaveRequestDto.getBirth())
                        .administratorMember(administratorMember)
                        .build();
                memberRepository.save(member);
            }
        } else {
            //예러 처리
            new Exception("로그인 해주세요");
        }
    }

    public Boolean validateToken(HttpServletRequest req) {
        final String token = req.getHeader("userEmail");
        final String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        return (userEmail.equals(administratorMember.getEmail()));
    }

    public String getUserEmail(HttpServletRequest req){
        final String token = req.getHeader("userEmail");
        final String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        return administratorMember.getEmail();
    }

    public Boolean CheckName(String name){
        Member member = memberRepository.findByMembername(name);
        if(member == null) {
            return true;
        }
        return false;
    }
}
