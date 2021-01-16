package com.helpme.MembershipFee.domain.deposit;

import com.helpme.MembershipFee.web.dto.DepositFindSumGroupByMemberNameDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAll();

    //총 입금 내역 조회
    @Query("select SUM(p.price) from Deposit p")
    Integer findSUMprice();

    //사용자별 입금 내역 조회
    //SQL : SELECT SAVENAME, SUM(PRICE) FROM DEPOSIT GROUP BY SAVENAME;
    @Query("select p.savename, SUM(p.price) from Deposit p group by p.savename")
    List<String> findSUMpriceBymember();

    List<Deposit> findBySavename(String name);
}
