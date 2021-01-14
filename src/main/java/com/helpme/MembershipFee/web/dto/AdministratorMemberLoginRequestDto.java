package com.helpme.MembershipFee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//관리자 로그인 클래스 DTO(데이터 교환)
public class AdministratorMemberLoginRequestDto {

    private String email;
    private String password;

}
