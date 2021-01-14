package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.member.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveRequestDto {


    private String membername;
    private String birth;

    public Member toEntity(){
        return Member.builder()
                .membername(membername)
                .birth(birth)
                .build();
    }

}
