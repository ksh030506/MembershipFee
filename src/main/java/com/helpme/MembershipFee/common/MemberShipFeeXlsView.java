package com.helpme.MembershipFee.common;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import com.helpme.MembershipFee.domain.member.Member;
import com.helpme.MembershipFee.domain.member.MemberRepository;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFee;
import com.helpme.MembershipFee.domain.membershipfee.MemberShipFeeRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("membershipfeeXls")
public class MemberShipFeeXlsView extends AbstractXlsView {


    @Autowired
    private MemberShipFeeRepository memberShipFeeRepository;

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.setHeader("Content-Disposition", "attachment; filename=\"membershipfee.xls\"");

        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));

        Sheet sheet = workbook.createSheet("deposit_sheet");

        List<MemberShipFee> memberShipFeeList = memberShipFeeRepository.findAll();

        // 헤더를 생성합니다
        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("순번");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("사용 금액");

        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("사용 이유");

        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("사용 날짜");

        //바이에 데이터를 넣어줍니다
        for(MemberShipFee memberShipFee : memberShipFeeList){
            Row bodyRow = sheet.createRow(rowIndex++);

            Cell bodyCell1 = bodyRow.createCell(0);
            bodyCell1.setCellValue(memberShipFee.getIdx_Membership_Fee());

            Cell bodyCell2 = bodyRow.createCell(1);
            bodyCell2.setCellValue(memberShipFee.getMembershipfee());

            Cell bodyCell3 = bodyRow.createCell(2);
            bodyCell3.setCellValue(memberShipFee.getPurposeofuse());

            Cell bodyCell4 = bodyRow.createCell(3);
            bodyCell4.setCellValue(memberShipFee.getDateofuse());
        }
    }
}
