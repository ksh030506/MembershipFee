package com.helpme.MembershipFee.service.memberShipFee;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.web.dto.MemberShipFeeSaveRequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MemberShipFeeService {

    //회비 사용 내역 저장
    Long save(MemberShipFeeSaveRequestDto memberShipFeeSaveRequestDto, HttpServletRequest req) throws Exception;

    //날짜 검색
    List<MemberShipFeeReturn> findByCreateDateBetween(HttpServletRequest req, String start, String end);

}
