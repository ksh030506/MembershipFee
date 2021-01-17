package com.helpme.MembershipFee.domain.deposit.apireturn;

import javax.persistence.*;
import java.time.LocalDate;

public interface DepositDateReturn {

    Long getIdx_Deposit();
    String getSavename();
    int getPrice();
    String getCreatedDate();
    String getIsPay();

}