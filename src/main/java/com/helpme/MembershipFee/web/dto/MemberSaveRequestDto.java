package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.members.Member;
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

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }

}
