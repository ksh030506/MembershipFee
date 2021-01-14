package com.helpme.MembershipFee.domain.administratorMember;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface AdministratorMemberRepository extends JpaRepository<AdministratorMember, Long> {
    AdministratorMember findByEmail(String email);
    Optional<AdministratorMember> findByName(String name);
}
