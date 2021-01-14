package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveRequestDto {

    private String name;
    private String email;
    private String password;

    public AdministratorMember toEntity(){
        return AdministratorMember.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

}
