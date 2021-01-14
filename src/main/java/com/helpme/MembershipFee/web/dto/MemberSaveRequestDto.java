package com.helpme.MembershipFee.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//회원 저장 클래스 DTO(데이터 교환)
public class MemberSaveRequestDto {

    private String membername;
    private String birth;

}
