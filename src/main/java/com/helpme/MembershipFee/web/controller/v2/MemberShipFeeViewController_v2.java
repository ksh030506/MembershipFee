package com.helpme.MembershipFee.web.controller.v2;


import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.MemberShipFeeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class MemberShipFeeViewController_v2 {

    @Autowired
    private MemberShipFeeViewService memberShipFeeViewService;

    @ResponseBody
    @GetMapping("/getmembershipfee")
    public List<MemberShipFeeReturn> findAll(HttpServletRequest req) throws Exception {
        return memberShipFeeViewService.findMemberShipFee(req);
    }
}
