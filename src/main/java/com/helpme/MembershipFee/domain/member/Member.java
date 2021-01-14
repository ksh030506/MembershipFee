package com.helpme.MembershipFee.domain.member;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Member;

    private String membername;
    private String birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idx_Admin")
    private AdministratorMember administratorMember;

}
