package com.helpme.MembershipFee.service.depositview;

import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import com.helpme.MembershipFee.web.dto.DepositFindByNameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DepositViewService {

    Page<DepositReturn> findAllPage(HttpServletRequest req, final Pageable pageable) throws Exception;

    Integer findSumPrice(HttpServletRequest req) throws Exception;

    List<DepositReturn> findName(DepositFindByNameDto depositFindByNameDto, HttpServletRequest req) throws Exception;
}
