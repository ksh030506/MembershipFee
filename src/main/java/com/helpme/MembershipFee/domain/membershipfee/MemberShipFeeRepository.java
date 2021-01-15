package com.helpme.MembershipFee.domain.membershipfee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberShipFeeRepository extends JpaRepository<MemberShipFee, Long> {
    List<MemberShipFee> findAll();
}
