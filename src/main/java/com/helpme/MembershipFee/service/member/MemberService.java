package com.helpme.MembershipFee.service.member;

import com.helpme.MembershipFee.domain.member.apireturn.nameReturn;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MemberService {

    Long save(HttpServletRequest req, HttpServletResponse res, MemberSaveRequestDto memberSaveRequestDto) throws Exception;

    List<nameReturn> GetUserName(HttpServletRequest req);

}
