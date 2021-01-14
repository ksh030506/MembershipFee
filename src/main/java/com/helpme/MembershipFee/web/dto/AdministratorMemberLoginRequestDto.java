package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdministratorMemberLoginRequestDto {

    private String email;
    private String password;

    public AdministratorMember toEntity(){
        return AdministratorMember.builder()
                .email(email)
                .password(password)
                .build();
    }

}
