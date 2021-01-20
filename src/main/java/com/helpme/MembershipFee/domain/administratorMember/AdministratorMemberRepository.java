package com.helpme.MembershipFee.domain.administratorMember;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdministratorMemberRepository extends JpaRepository<AdministratorMember, Long> {
    //이메일을 기준으로 찾음(타입 : AdministratorMember)
    AdministratorMember findByEmail(String email);

    //이름을 기준으로 찾음(타입 : AdministratorMember)
    AdministratorMember findByName(String name);
}
