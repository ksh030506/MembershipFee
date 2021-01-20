package com.helpme.MembershipFee.domain.membershipfee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MemberShipFee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idx_Membership_Fee;

    private String purposeofuse;
    private int membershipfee;

    //회비 사용 날짜
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private String dateofuse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Idx_Admin")
    private AdministratorMember administratorMember;

}
