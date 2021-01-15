package com.helpme.MembershipFee.web.dto;

import com.helpme.MembershipFee.domain.deposit.Deposit;

import java.time.LocalDateTime;

public class DepositViewResponse {

    private String savename;
    private int price;
    private LocalDateTime Create_Deposit;

    public DepositViewResponse(Deposit entity){
        this.savename = entity.getSavename();
        this.price = entity.getPrice();
        this.Create_Deposit = entity.getCreatedDate();
    }

}
