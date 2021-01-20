package com.helpme.MembershipFee.service.member;

import com.helpme.MembershipFee.domain.member.apireturn.nameReturn;
import com.helpme.MembershipFee.web.dto.MemberSaveRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MemberService {

    //회원 저장
    Long save(HttpServletRequest req, HttpServletResponse res, MemberSaveRequestDto memberSaveRequestDto) throws Exception;

    //회원 이름 모두 가져오기
    List<nameReturn> GetUserName(HttpServletRequest req);

}
