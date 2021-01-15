package com.helpme.MembershipFee.web.dto;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//회원 저장 클래스 DTO(데이터 교환)
public class MemberSaveRequestDto {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private String membername;
    private String birth = simpleDateFormat.format(new Date());;

}
