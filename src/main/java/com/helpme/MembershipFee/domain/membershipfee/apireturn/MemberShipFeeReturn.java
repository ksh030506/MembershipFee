package com.helpme.MembershipFee.domain.membershipfee.apireturn;

//JPA Return 데이터를 조작하기 위해 사용
public interface MemberShipFeeReturn {

    String getPurposeofuse();
    int getMembershipfee();
    String getDateofuse();

}
