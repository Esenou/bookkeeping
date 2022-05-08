package com.bookkeeping.kg.service.report;

import com.bookkeeping.kg.model.SalaryDto;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class ExcelService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Row> rows;
    private Locale locale;
    private MessageSource messageSource;
    private  List<SalaryDto> list;

    public ExcelService(List<SalaryDto> list, MessageSource messageSource) {
        workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("AccountHistory");
        this.locale = locale;
        this.list = list;
        this.rows = new ArrayList<>();
        this.messageSource = messageSource;
        IntStream.range(0, 16 + list.size()).forEach(i -> rows.add(sheet.createRow(i)));
    }
    String getLocaleMessage(String message){
        return messageSource.getMessage(message, null, locale);
    }

    private void writeHeader() {

        CellStyle styleHaeder = workbook.createCellStyle();
        CellStyle styleSample = workbook.createCellStyle();
        XSSFFont fontHeader = workbook.createFont();
        XSSFFont fontSample = workbook.createFont();
        fontSample.setFontHeight(10);
        styleSample.setFont(fontSample);
        fontHeader.setBold(true);
        fontHeader.setFontHeight(10);
        styleHaeder.setFont(fontHeader);
        int columnNum = 0;

        createCell(rows.get(0), 0, getLocaleMessage("account.history.dear"), styleHaeder);
     //   createCell(rows.get(0), 1, history.getOwnerName(), styleSample);
        createCell(rows.get(0), 2, getLocaleMessage("account.history.date"), styleHaeder);
      //  createCell(rows.get(0), 3, history.getExtraInfo().get("today"), styleSample);

        createCell(rows.get(1), 0, getLocaleMessage("account.history.address"),  styleHaeder);
       // createCell(rows.get(1), 1, history.getAddress(), styleSample);
        createCell(rows.get(1), 2, getLocaleMessage("account.history.customerNo"), styleHaeder);
       // createCell(rows.get(1), 3, history.getCustNo(), styleSample);

        createCell(rows.get(2), 2, getLocaleMessage("account.history.accountNo"), styleHaeder);
       // createCell(rows.get(2), 3, history.getAccountNo() + " " + history.getCurrency(), styleSample);

        createCell(rows.get(3), 2, getLocaleMessage("account.history.externalAcccount"), styleHaeder);
       // createCell(rows.get(3), 3, history.getExternalAccountNo(), styleSample);

        createCell(rows.get(4), 2, getLocaleMessage("account.history.branch"), styleHaeder);
       // createCell(rows.get(4), 3, history.getBranch().getName() + " " + history.getBranch().getName(), styleSample);

        createCell(rows.get(5), 2, getLocaleMessage("account.history.rnn"), styleHaeder);
       // createCell(rows.get(5), 3, history.getRnn(), styleSample);

        createCell(rows.get(6), 2, getLocaleMessage("account.history.statementPeriod"), styleHaeder);
       // createCell(rows.get(6), 3, history.getExtraInfo().get("startDate") + " / " + history.getExtraInfo().get("endDate"), styleSample);

        createCell(rows.get(8), 0, getLocaleMessage("account.history.incoming"),  styleHaeder);
        //createCell(rows.get(8), 1, history.getExtraInfo().get("finalOpeningBalance"), styleSample);
        createCell(rows.get(9), 0, getLocaleMessage("account.history.outgoing"), styleHaeder);
        //createCell(rows.get(9), 1, history.getExtraInfo().get("finalClosingBalance"), styleSample);

        sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 2));
        //createCell(rows.get(11), 0, history.getTransactions().size() + "  " + getLocaleMessage("account.history.text"), styleHaeder);

        createCell(rows.get(13), 0, getLocaleMessage("account.history.no"), styleHaeder);
        createCell(rows.get(13), 1, getLocaleMessage("account.history.paymentNumber"), styleHaeder);
        createCell(rows.get(13), 2, getLocaleMessage("account.history.transaction.date"), styleHaeder);
        createCell(rows.get(13), 3, getLocaleMessage("account.history.valuation.date"), styleHaeder);
        createCell(rows.get(13), 4, getLocaleMessage("account.history.transaction.type"), styleHaeder);
        createCell(rows.get(13), 5, getLocaleMessage("account.history.transaction.beneficiary"), styleHaeder);
        createCell(rows.get(13), 6, getLocaleMessage("account.history.rnn"), styleHaeder);
        createCell(rows.get(13), 7, getLocaleMessage("account.history.accountNobeneficiary"), styleHaeder);
        createCell(rows.get(13), 8, getLocaleMessage("account.history.transaction.purpose"), styleHaeder);
        createCell(rows.get(13), 9, getLocaleMessage("account.history.transaction.sum"), styleHaeder);
        createCell(rows.get(13), 10, getLocaleMessage("account.history.balance"), styleHaeder);

    }

    private void writeTransactionDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(8);
        style.setFont(font);
       list.forEach(tr -> {
            int columnNum = 0;
            createCell(this.rows.get(14), columnNum, tr.getWorkerName(), style);
            createCell(this.rows.get(14), columnNum + 1, tr.getProduct().toString(), style);
            /*createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 2, tr.getTranDate().toString(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 3, tr.getValorDate().toString(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 4, tr.getTranType(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 5, (null == tr.getSenderName()?"":tr.getSenderName()) + " / " + (null == tr.getReceipientName()?"":tr.getReceipientName()), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 6, (null == tr.getRnn()?"": tr.getRnn()) + " / " + (null == tr.getRnn2()? "": tr.getRnn2()), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 7, tr.getRecipientAccount(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 8, tr.getTranDesc(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 9, tr.getTranSum(), style);
            createCell(this.rows.get(14 + history.getTransactions().indexOf(tr)), columnNum + 10, tr.getBalance(), style);
*/        });
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        if (null == value)
            value = new String("");
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if (value instanceof String) {
            cell.setCellValue((String) value);
        }else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate) value);
        }
        style.setAlignment(HorizontalAlignment.LEFT);
        cell.setCellStyle(style);

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeader();
        writeTransactionDataLines();
        ByteArrayInputStream inputStream = null;
        OutputStream outputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        String fileName = "salary_report";
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.xlsx\"",fileName));

            outputStream = response.getOutputStream();
            inputStream = new ByteArrayInputStream(buffer);
            IOUtils.copy(inputStream, outputStream);
            workbook.close();

        } finally {
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (Exception e) {
                //logger.error("Error message: {}", e.toString());
            }
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception ex) {
                //logger.error("Error message: {}", ex.toString());
            }
            try {
                if (byteArrayOutputStream != null)
                    byteArrayOutputStream.close();
            } catch (Exception ex) {
                //logger.error("Error message: {}", ex.toString());
            }
        }
        //logger.info("Excel file created successfully. [{}.xlsx]", fileName);
    }
}
