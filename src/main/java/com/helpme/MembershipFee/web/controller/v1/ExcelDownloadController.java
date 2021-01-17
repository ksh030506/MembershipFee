package com.helpme.MembershipFee.web.controller.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExcelDownloadController {

    @GetMapping(path="/download/sample", produces = "application/vnd.ms-excel")
    public String DepositExcelDownload(){
        return "depositXls";
    }

}
