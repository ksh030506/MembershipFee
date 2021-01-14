package com.helpme.MembershipFee.domain.administratorMember;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdministratorMember extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Admin;

    private String name;
    private String email;
    private String password;

}