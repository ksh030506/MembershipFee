package com.helpme.MembershipFee.domain.deposit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.member.Member;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idx_Deposit;

    private String savename;
    private int price;

    @Enumerated(EnumType.STRING)
    private Deposit_IsPay isPay;

    @ManyToOne
    @JoinColumn(name = "Idx_Member")
    private Member member;

}
