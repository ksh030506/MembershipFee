package com.helpme.MembershipFee.service.administratorMember;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.web.dto.AdministratorMemberLoginRequestDto;
import com.helpme.MembershipFee.web.dto.AdministratorMemberSaveRequestDto;

public interface AdministratorMemberService {

    Long save(AdministratorMemberSaveRequestDto administratorMemberSaveRequestDto) throws Exception;

    AdministratorMember findByEmail(AdministratorMemberLoginRequestDto administratorMemberLoginRequestDto) throws Exception;

}
