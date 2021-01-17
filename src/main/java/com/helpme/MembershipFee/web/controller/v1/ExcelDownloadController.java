package com.helpme.MembershipFee.web.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
@Controller
public class ExcelDownloadController {

    @GetMapping(path="/download/deposit", produces = "application/vnd.ms-excel")
    public String DepositExcelDownload(){
        return "depositXls";
    }

    @GetMapping(path = "/download/membershipfee", produces = "application/vnd.ms-excel")
    public String MemberShipFeeExcelDownload(){
        return "membershipfeeXls";
    }

}
