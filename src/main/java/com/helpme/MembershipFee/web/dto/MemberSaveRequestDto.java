package com.helpme.MembershipFee.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSaveRequestDto {

    private String membername;
    private String birth;

}
