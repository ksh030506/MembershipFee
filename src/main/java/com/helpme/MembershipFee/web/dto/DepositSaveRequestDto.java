package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.Deposit_IsPay;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepositSaveRequestDto {

    private String savename;
    private int price;
    private Boolean deposit_isPay;

}
