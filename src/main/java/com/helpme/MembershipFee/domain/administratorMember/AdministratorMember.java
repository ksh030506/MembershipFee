package com.helpme.MembershipFee.domain.administratorMember;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdministratorMember extends BaseTimeEntity {

    //비즈니스 로직에 포함되지 않은 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Idx_Admin;

    //관리자 이름
    private String name;
    //관리자 이메일
    private String email;
    //관리자 비밀번호(암호화)
    private String password;

}