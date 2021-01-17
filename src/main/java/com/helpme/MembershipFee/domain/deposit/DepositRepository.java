package com.helpme.MembershipFee.domain.deposit;

import com.helpme.MembershipFee.domain.deposit.apireturn.DepositReturn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    //총 입금 내역 조회
    @Query("select SUM(p.price) from Deposit p")
    Integer findSUMprice();

    //사용자별 입금 내역 조회
    //SQL : SELECT SAVENAME, SUM(PRICE) FROM DEPOSIT GROUP BY SAVENAME;
    @Query("select p.savename, SUM(p.price) from Deposit p group by p.savename")
    List<String> findSUMpriceBymember();

    //사용자 이름 조회
    List<DepositReturn> findBySavename(String name);

    //미납내역 조회 (전체)
    List<Deposit> findByIsPay(Deposit_IsPay deposit_isPay);

    //Deposit 조회 + 페이징 처리
    Page<DepositReturn> findAllBy(Pageable pageable);

}
