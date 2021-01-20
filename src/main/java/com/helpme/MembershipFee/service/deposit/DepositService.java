package com.helpme.MembershipFee.service.deposit;

import com.helpme.MembershipFee.domain.deposit.Deposit_IsPay;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositDateReturn;
import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.web.dto.DepositSaveRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

public interface DepositService {

    //입금
    Long save(DepositSaveRequestDto depositSaveRequestDto, HttpServletRequest req) throws Exception;

    //입금 여부 확인
    List<DepositReturn> findByIsPay(Deposit_IsPay deposit_isPay, HttpServletRequest req);

    //날짜 검색
    List<DepositDateReturn> findByCreateDateBetween(HttpServletRequest req, LocalDate start, LocalDate end);

}
