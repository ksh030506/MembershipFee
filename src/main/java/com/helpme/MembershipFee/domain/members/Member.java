package com.helpme.MembershipFee.domain.members;

import com.helpme.MembershipFee.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Admin;

    @Column(name = "Email")
    private String Email;

    @Column(name = "Password")
    private String Password;

}
