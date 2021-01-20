package com.helpme.MembershipFee.service.administratorMember;

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
public class AdministratorMemberServiceImpl implements AdministratorMemberService{

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @Override
    @Transactional
    public Long save(AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception {
        //이메일 형식 검사
        isValidEmail(administratorMemberSaveRequestDto.getEmail());
        //이메일 중복 검사
        checkEmail(administratorMemberSaveRequestDto.getEmail());
        //이름 중복 검사
        checkName(administratorMemberSaveRequestDto.getName());
        //비밀번호 암호화
        PasswordEncoder passwordEncoder = new PasswordEncoding();
        String newPassword1 = passwordEncoder.encode(administratorMemberSaveRequestDto.getPassword());
        administratorMemberSaveRequestDto.setPassword(newPassword1);
        return administratorMemberRepository.save(administratorMemberSaveRequestDto.toEntity()).getIdx_Admin();
    }

    //이메일 중복 검사
    public boolean checkEmail(String email) throws Exception {
        AdministratorMember member = administratorMemberRepository.findByEmail(email);
        if(member != null){
            throw new Exception("이메일 중복");
        }
        return true;
    }

    //이름 중복 검사
    public boolean checkName(String name) throws Exception {
        AdministratorMember member = administratorMemberRepository.findByName(name);
        System.out.println(member);
        if(member != null){
            throw new Exception("이름 중복");
        }
        return true;
    }

    //이메일 형식 검사
    public boolean isValidEmail(String email) throws Exception {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(!m.matches()) {
            throw new Exception("이메일 형식을 지켜주세요");
        }
        return true;
    }


    @Override
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
