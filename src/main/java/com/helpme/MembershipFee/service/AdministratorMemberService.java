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
    public Long save(AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        if(!checkEmail(administratorMemberSaveRequestDto.getEmail())){
            throw new Exception("이메일 중복");
        } else if(!isValidEmail(administratorMemberSaveRequestDto.getEmail())){
            throw new Exception("이메일 형식을 지켜주세요");
        } else if(!checkName(administratorMemberSaveRequestDto.getName())){
            throw new Exception("이름 중복");
        }
        //비밀번호 검사
        chekPassword(administratorMemberSaveRequestDto.getPassword());
        //비밀번호 암호화
        PasswordEncoder passwordEncoder = new PasswordEncoding();
        String newPassword1 = passwordEncoder.encode(administratorMemberSaveRequestDto.getPassword());
        administratorMemberSaveRequestDto.setPassword(newPassword1);
        return administratorMemberRepository.save(administratorMemberSaveRequestDto.toEntity()).getIdx_Admin();
    }

    //비밀번호 검사
    public Boolean chekPassword(String password) throws Exception {
        if(password.length() >= 10){
            //영어 대문자, 소문자, 숫자, 특수문자 중 2종류 조합(정규식)
            String regex = "";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            if(!m.matches()) {
                throw new Exception("영어 대문자, 소문자, 숫자, 특수문자 중 2종류를 조합하여 사용해주세요");
            }
        } else if(password.length() < 10 && password.length() >= 8){
            //영어 대문자, 소문자, 숫자, 특수문자 중 3종류 조합(정규식)
            String regex = "";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            if(!m.matches()) {
                throw new Exception("영어 대문자, 소문자, 숫자, 특수문자 중 3종류를 조합하여 사용해주세요");
            }
        } else if(password.length() < 8){
            throw new Exception("비밀번호는 8자리 이상작성해 주세요");
        }
        return true;
    }

    //이메일 중복 검사
    public Boolean checkEmail(String email){
        AdministratorMember member = administratorMemberRepository.findByEmail(email);
        if(member == null){
            return true;
        }
        return false;
    }

    //이름 중복 검사
    public Boolean checkName(String name){
        Optional<AdministratorMember> member = administratorMemberRepository.findByName(name);
        if(member.isEmpty()){
            return true;
        }
        return false;
    }

    //이메일 형식 검사
    public boolean isValidEmail(String email) {
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
        //저장되어 있는 암호화 비밀번호와 Request한 비밀번호를 PasswordEncoder로 비교
        else if(passwordEncoder.matches(administratorMemberLoginRequestDto.getPassword(), member.getPassword()) == false){
            throw new Exception ("비밀번호가 틀립니다.");
        }
        return member;
    }
}
