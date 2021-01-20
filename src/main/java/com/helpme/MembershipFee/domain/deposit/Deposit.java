package com.helpme.MembershipFee.domain.deposit;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Deposit extends BaseTimeEntity {

    //비즈니스 로직에 포함되지 않은 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idx_Deposit;

    //입금 회원
    private String savename;
    //입금 금액
    private int price;

    //입금 여부
    @Enumerated(EnumType.STRING)
    private Deposit_IsPay isPay;

    //어떤 멤버가 입금했는지 알 수 있는 FK
    //Table 설계 X, 객체지향적 설계 O
    @ManyToOne
    @JoinColumn(name = "Idx_Member")
    private Member member;

}
