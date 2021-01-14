package com.helpme.MembershipFee.web.dto;

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

}
