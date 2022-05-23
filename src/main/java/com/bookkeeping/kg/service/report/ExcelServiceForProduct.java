package com.bookkeeping.kg.service.report;

import com.bookkeeping.kg.model.ReportsDto;
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


public class ExcelServiceForProduct {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Row> rows;
    private Locale locale;
    private MessageSource messageSource;
    private List<ReportsDto>  reportsDtoList;

    public ExcelServiceForProduct(List<ReportsDto> reportsDtoList, MessageSource messageSource, Locale locale) {
        workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("выработка");
        this.locale = locale;
        this.reportsDtoList = reportsDtoList;
        this.rows = new ArrayList<>();
        this.messageSource = messageSource;
        IntStream.range(0, 16 + reportsDtoList.size()).forEach(i -> rows.add(sheet.createRow(i)));
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

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));
        createCell(rows.get(0), 0, getLocaleMessage("rep.description"), styleHaeder);


        createCell(rows.get(1), 0, getLocaleMessage("rep.productName"), styleHaeder);
        createCell(rows.get(1), 1, getLocaleMessage("rep.productType"), styleHaeder);
        createCell(rows.get(1), 2, getLocaleMessage("rep.product"), styleHaeder);
        createCell(rows.get(1), 3, getLocaleMessage("rep.brakWorkers"), styleHaeder);
        createCell(rows.get(1), 4, getLocaleMessage("rep.brakStanok"), styleHaeder);
        createCell(rows.get(1), 5, getLocaleMessage("rep.brakSay"), styleHaeder);
        createCell(rows.get(1), 6, getLocaleMessage("rep.all"), styleHaeder);


    }

    private void writeTransactionDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(8);
        style.setFont(font);

        reportsDtoList.forEach(tr -> {
            int columnNum = 0;
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum, tr.getProductName(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 1, tr.getProductType().toString(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 2, tr.getProduct().toString(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 3, tr.getBrakWorkers(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 4, tr.getBrakStanok(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 5, tr.getBrakSay(), style);
            createCell(this.rows.get(2 + reportsDtoList.indexOf(tr)), columnNum + 6, tr.getAll(), style);
        });
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
        String fileName = "report";
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
