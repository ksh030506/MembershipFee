package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.domain.members.Member;
import com.helpme.MembershipFee.domain.members.MemberRepository;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Long save(MemberSaveRequestDto memberSaveRequestDto) throws Exception {
        if(checkEmail(memberSaveRequestDto.getEmail()) == false){
            throw new Exception("이메일 중복");
        } else if(isValidEmail(memberSaveRequestDto.getEmail()) == false){
            throw new Exception("이메일 형식을 지켜주세요");
        } else if(checkName(memberSaveRequestDto.getName()) == false){
            throw new Exception("이름 중복");
        }
//        PasswordEncoder passwordEncoder = new PasswordEncoding();
//        String newPassword1 = passwordEncoder.encode(authSaveRequestDto.getPassword());
//        System.out.println(newPassword1);
//        authSaveRequestDto.setPassword(newPassword1);
//        authRepository.save(authSaveRequestDto.toEntity());
        return memberRepository.save(memberSaveRequestDto.toEntity()).getIdx_Admin();
    }

    public Boolean checkEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isEmpty()){
            return true;
        }
        return false;
    }

    public Boolean checkName(String name){
        Optional<Member> member = memberRepository.findByName(name);
        if(member.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        boolean err = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            err = true;
        }
        return err;
    }




}
