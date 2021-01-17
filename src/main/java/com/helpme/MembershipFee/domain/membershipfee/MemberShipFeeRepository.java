package com.helpme.MembershipFee.domain.membershipfee;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MemberShipFeeRepository extends JpaRepository<MemberShipFee, Long> {
    Page<MemberShipFeeReturn> findAllBy(Pageable pageable);

    //날짜 검색
    @Query("SELECT m FROM MemberShipFee m WHERE m.dateofuse BETWEEN :start AND :end")
    List<MemberShipFeeReturn> findAllDateBetween(String start, String end);

}
