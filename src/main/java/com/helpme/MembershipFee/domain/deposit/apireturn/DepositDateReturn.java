package com.helpme.MembershipFee.domain.deposit.apireturn;

import java.time.LocalDate;

//JPA Return 데이터를 조작하기 위해 사용
public interface DepositDateReturn {

    String getSavename();
    int getPrice();
    LocalDate getCreatedDate();
    String getIsPay();

}