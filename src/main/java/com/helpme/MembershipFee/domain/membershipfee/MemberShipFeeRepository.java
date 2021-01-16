package com.helpme.MembershipFee.domain.membershipfee;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberShipFeeRepository extends JpaRepository<MemberShipFee, Long> {
    List<MemberShipFeeReturn> findAllBy();
    Page<MemberShipFeeReturn> findAllBy(Pageable pageable);
}
