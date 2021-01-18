package com.helpme.MembershipFee.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//회비 사용 내역 클래스 DTO(데이터 교환)
public class MemberShipFeeSaveRequestDto {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    private String purposeofuse;
    private String dateofuse = simpleDateFormat.format(new Date());
    private int membershipfee;

}
