package com.helpme.MembershipFee.domain.members;

import com.helpme.MembershipFee.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Admin;

    private String name;
    private String email;
    private String password;

}