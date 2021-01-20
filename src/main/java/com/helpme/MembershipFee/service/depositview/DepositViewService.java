package com.helpme.MembershipFee.service.depositview;

import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DepositViewService {

    //입금 내역 조회 + 페이징 처리
    Page<DepositReturn> findAllPage(HttpServletRequest req, final Pageable pageable) throws Exception;

    //입금 총 금액
    Integer findSumPrice(HttpServletRequest req) throws Exception;

    //이름 검색
    List<DepositReturn> findName(DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception;
}
