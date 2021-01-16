package com.helpme.MembershipFee.domain.deposit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpme.MembershipFee.common.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Deposit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Deposit;

    private String savename;
    private int price;

}
