package com.helpme.MembershipFee.web.controller.v1;


import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.MemberShipFeeService;
import com.helpme.MembershipFee.service.MemberShipFeeViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class MemberShipFeeViewController {

    @Autowired
    private MemberShipFeeViewService memberShipFeeViewService;

    @ResponseBody
    @GetMapping("/getmembershipfee")
    public List<MemberShipFee> findAll(HttpServletRequest req) throws Exception {
        return memberShipFeeViewService.findMemberShipFee(req);
    }
}
