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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
    public void save(HttpServletRequest req, HttpServletResponse res, MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        final String token = req.getHeader("userEmail");
        String userEmail = jwtUtil.getUserEmail(token);
        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
        Member member = Member.builder()
                .membername(memberSaveRequestDto.getMembername())
                .birth(memberSaveRequestDto.getBirth())
                .administratorMember(administratorMember)
                .build();

        if(jwtUtil.validateToken(token, administratorMember) == true) {
            if(CheckName(memberSaveRequestDto.getMembername()) == false){
                res.setContentType("text/html; charset=UTF-8");
                PrintWriter out = res.getWriter();
                out.println("<script>alert('이름이 중복되었습니다.'); history.go(-1);</script>");
                out.flush();
            } else {
                memberRepository.save(member);
            }
        }
    }

    public Boolean CheckName(String name){
        Member member = memberRepository.findByMembername(name);
        if(member == null) {
            return true;
        }
        return false;
    }
}
