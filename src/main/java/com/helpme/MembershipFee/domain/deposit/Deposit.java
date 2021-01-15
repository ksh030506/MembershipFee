package com.helpme.MembershipFee.domain.deposit;

import com.helpme.MembershipFee.common.BaseTimeEntity;
import com.helpme.MembershipFee.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Idx_Deposit;

    private String savename;
    private int price;

    //생성 날짜
    @CreatedDate
    private LocalDateTime createdDate;

}
