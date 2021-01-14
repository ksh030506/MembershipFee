package com.helpme.MembershipFee.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //이름을 기준으로 찾음(타입 : Member)
    Member findByMembername(String name);
    List<apiReturn> findAllBy();
}
