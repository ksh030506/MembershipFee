package com.helpme.MembershipFee.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Date;

//부모 클래스로 만들어 모든 파일에 상속
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//생성 날짜 자동 생성 클래스
public abstract class BaseTimeEntity {

    //생성 날짜
    @CreatedDate
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate createdDate;

    public LocalDate getCreatedDate() {
        return createdDate;
    }

}