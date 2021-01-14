package com.helpme.MembershipFee.domain.deposit;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.member.Member;
import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idx_Member")
    private Member member;

}
