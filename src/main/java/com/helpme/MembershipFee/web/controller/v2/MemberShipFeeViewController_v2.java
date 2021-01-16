package com.helpme.MembershipFee.web.controller.v2;

import com.helpme.MembershipFee.domain.membershipfee.apireturn.MemberShipFeeReturn;
import com.helpme.MembershipFee.service.MemberShipFeeViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v2")
public class MemberShipFeeViewController_v2 {

    @Autowired
    private MemberShipFeeViewService memberShipFeeViewService;

    @ResponseBody
    @GetMapping("/getmembershipfee")
    public Page<MemberShipFeeReturn> findAll(HttpServletRequest req, Pageable pageable) throws Exception {
        return memberShipFeeViewService.findMemberSipFeepage(req, pageable);
    }
}
