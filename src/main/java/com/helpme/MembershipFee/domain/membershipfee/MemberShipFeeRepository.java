package com.helpme.MembershipFee.domain.membershipfee;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberShipFeeRepository extends JpaRepository<MemberShipFee, Long> {
    Page<MemberShipFeeReturn> findAllBy(Pageable pageable);
}
