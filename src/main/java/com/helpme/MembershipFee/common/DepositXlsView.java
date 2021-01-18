package com.helpme.MembershipFee.common;

import com.helpme.MembershipFee.domain.deposit.Deposit;
import com.helpme.MembershipFee.domain.deposit.DepositRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("depositXls")
public class DepositXlsView extends AbstractXlsView {

    @Autowired
    private DepositRepository depositRepository;

    @Override
    protected void buildExcelDocument(Map<String, Object> map, Workbook workbook, HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.setHeader("Content-Disposition", "attachment; filename=\"deposit.xls\"");

        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));

        Sheet sheet = workbook.createSheet("deposit_sheet");

        //엑셀 렌더링에 필요한 데이터
        List<Deposit> depositList = depositRepository.findAll();

        //헤더 생성
        int rowIndex = 0;
        Row headerRow = sheet.createRow(rowIndex++);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("순번");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("이름");

        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("회비");

        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("날짜");


        //엑셀 바디에 데이터 넣어주기
        for(Deposit deposit : depositList){
            Row bodyRow = sheet.createRow(rowIndex++);

            Cell bodyCell1 = bodyRow.createCell(0);
            bodyCell1.setCellValue(deposit.getIdx_Deposit());

            Cell bodyCell2 = bodyRow.createCell(1);
            bodyCell2.setCellValue(deposit.getSavename());

            Cell bodyCell3 = bodyRow.createCell(2);
            bodyCell3.setCellValue(deposit.getPrice());

            //에러 => 날짜 형식
            Cell bodyCell4 = bodyRow.createCell(3);
            bodyCell4.setCellValue(deposit.getCreatedDate());
        }
    }
}
