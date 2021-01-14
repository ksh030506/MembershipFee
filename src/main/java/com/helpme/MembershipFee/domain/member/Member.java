package com.helpme.MembershipFee.domain.member;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    //비즈니스 로직에 포함되지 않은 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idx_Member;

    //유저 이름
    private String membername;
    //유저 생일
    private String birth;

    //어떤 관리자가 추가했는지 알 수 있는 FK
    //LAZY => 지연로딩 (나중에 fetchJoin으로 해결)
    //Table 설계 X, 객체지향적 설계 O
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idx_Admin")
    private AdministratorMember administratorMember;

}
