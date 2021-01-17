package com.helpme.MembershipFee.web.controller.v1;

import com.helpme.MembershipFee.common.JwtUtil;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import com.helpme.MembershipFee.domain.administratorMember.AdministratorMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1")
@Controller
public class ExcelDownloadController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdministratorMemberRepository administratorMemberRepository;

    @GetMapping(path="/download/deposit", produces = "application/vnd.ms-excel")
    public String DepositExcelDownload(HttpServletRequest req){
          //현재 API만 구현되어있기때문에 사용자 인증은 주석처리
//        final String token = req.getHeader("userEmail");
//        String userEmail = jwtUtil.getUserEmail(token);
//        AdministratorMember administratorMember = administratorMemberRepository.fin에dByEmail(userEmail);
//        jwtUtil.validateToken(token, administratorMember);
        return "depositXls";
    }

    @GetMapping(path = "/download/membershipfee", produces = "application/vnd.ms-excel")
    public String MemberShipFeeExcelDownload(HttpServletRequest req){
          //현재 API만 구현되어있기때문에 사용자 인증은 주석처리
//        final String token = req.getHeader("userEmail");
//        String userEmail = jwtUtil.getUserEmail(token);
//        AdministratorMember administratorMember = administratorMemberRepository.findByEmail(userEmail);
//        jwtUtil.validateToken(token, administratorMember);
        return "membershipfeeXls";
    }

}
