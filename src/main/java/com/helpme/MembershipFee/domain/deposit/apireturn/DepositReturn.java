package com.helpme.MembershipFee.domain.deposit.apireturn;

import java.time.LocalDate;

public interface DepositReturn {

    int getPrice();
    String getSavename();
    LocalDate getCreatedDate();
    String getIsPay();

}
