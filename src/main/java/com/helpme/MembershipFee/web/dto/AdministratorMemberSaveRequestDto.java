package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//관리자 회원가입 클래스 DTO(데이터 교환)
public class AdministratorMemberSaveRequestDto {

    private String name;
    private String email;
    private String password;

    //toEntity호출 => AdministratorMember builder 호출
    public AdministratorMember toEntity(){
        return AdministratorMember.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
