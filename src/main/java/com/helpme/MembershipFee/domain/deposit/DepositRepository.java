package com.helpme.MembershipFee.domain.deposit;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findAll();

    @Query("select SUM(p.price) from Deposit p")
    Integer findSUMprice();

    List<Deposit> findBySavename(String name);
}
