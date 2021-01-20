package com.helpme.MembershipFee.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//입금 저정 클래스 DTO(데이터 교환)
public class DepositSaveRequestDto {

    private String savename;
    private int price;
    private Boolean deposit_isPay;

}
