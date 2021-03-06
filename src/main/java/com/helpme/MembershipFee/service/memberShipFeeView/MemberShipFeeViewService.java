package com.helpme.MembershipFee.service.memberShipFeeView;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface MemberShipFeeViewService {

    //회비 사용 내역 조회 + 페이징 처리
    Page<MemberShipFeeReturn> findMemberSipFeepage(HttpServletRequest req, final Pageable pageable) throws Exception;

}
