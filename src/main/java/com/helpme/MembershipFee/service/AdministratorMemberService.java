package com.helpme.MembershipFee.service;

import com.helpme.MembershipFee.common.PasswordEncoding;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdministratorMemberService {

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Transactional
    public void save(AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        if(checkEmail(administratorMemberSaveRequestDto.getEmail()) == false){
            throw new Exception("이메일 중복");
        } else if(isValidEmail(administratorMemberSaveRequestDto.getEmail()) == false){
            throw new Exception("이메일 형식을 지켜주세요");
        } else if(checkName(administratorMemberSaveRequestDto.getName()) == false){
            throw new Exception("이름 중복");
        }
        PasswordEncoder passwordEncoder = new PasswordEncoding();
        String newPassword1 = passwordEncoder.encode(administratorMemberSaveRequestDto.getPassword());
        System.out.println(newPassword1);
        administratorMemberSaveRequestDto.setPassword(newPassword1);
        administratorMemberRepository.save(administratorMemberSaveRequestDto.toEntity());
    }

    public Boolean checkEmail(String email){
        AdministratorMember member = administratorMemberRepository.findByEmail(email);
        if(member == null){
            return true;
        }
        return false;
    }

    public Boolean checkName(String name){
        Optional<AdministratorMember> member = administratorMemberRepository.findByName(name);
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

    @Transactional
    public AdministratorMember findByEmail(AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto) throws Exception {
        PasswordEncoder passwordEncoder = new PasswordEncoding();
        AdministratorMember member = administratorMemberRepository.findByEmail(administratorMemberLoginRequestDto.getEmail());

        if(member == null){
            throw new Exception ("아이디가 없음");
        }
        if(passwordEncoder.matches(administratorMemberLoginRequestDto.getPassword(), member.getPassword()) == false){
            throw new Exception ("비밀번호가 틀립니다.");
        }
        return member;
    }
}
