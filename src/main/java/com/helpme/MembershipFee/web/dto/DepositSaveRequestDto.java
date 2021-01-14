package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositSaveRequestDto {

    private String savename;
    private int price;

    public Deposit toEntity(){
        return Deposit.builder()
                .savename(savename)
                .price(price)
                .build();
    }
}
