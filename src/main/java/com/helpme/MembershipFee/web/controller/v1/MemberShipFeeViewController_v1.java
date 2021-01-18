package com.helpme.MembershipFee.web.controller.v1;


import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.MemberShipFeeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class MemberShipFeeViewController_v1 {

    @Autowired
    private MemberShipFeeViewService memberShipFeeViewService;

    //회비 사용 내역 조회 엔트포인트
    @ResponseBody
    @GetMapping("/getmembershipfee")
    public Page<MemberShipFeeReturn> findAll(HttpServletRequest req, Pageable pageable) throws Exception {
        return memberShipFeeViewService.findMemberSipFeepage(req, pageable);
    }

}
