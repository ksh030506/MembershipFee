package com.helpme.MembershipFee.domain.deposit.apireturn;

import javax.persistence.*;
import java.time.LocalDate;

public interface DepositDateReturn {

    String getSavename();
    int getPrice();
    LocalDate getCreatedDate();
    String getIsPay();

}