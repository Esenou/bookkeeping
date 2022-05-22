package com.bookkeeping.kg.service.report;

import com.bookkeeping.kg.model.SalaryDto;
import com.bookkeeping.kg.model.SalaryInfoDto;
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
    private SalaryDto salaryDto;

    public ExcelService(SalaryDto salaryDto, MessageSource messageSource,Locale locale) {
        workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("AccountHistory");
        this.locale = locale;
        this.salaryDto = salaryDto;
        this.rows = new ArrayList<>();
        this.messageSource = messageSource;
        IntStream.range(0, 16 + salaryDto.getSalaryDetailInfoDtoList().size()).forEach(i -> rows.add(sheet.createRow(i)));
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
        createCell(rows.get(0), 0, getLocaleMessage("report.description"), styleHaeder);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 15));
        createCell(rows.get(0), 10, getLocaleMessage("report.salary.description"), styleHaeder);

        createCell(rows.get(1), 0, getLocaleMessage("report.productName"), styleHaeder);
        createCell(rows.get(1), 1, getLocaleMessage("report.productType"), styleHaeder);
        createCell(rows.get(1), 2, getLocaleMessage("report.workerName"), styleHaeder);
        createCell(rows.get(1), 3, getLocaleMessage("report.product.name"), styleHaeder);
        createCell(rows.get(1), 4, getLocaleMessage("report.workerBrak"), styleHaeder);
        createCell(rows.get(1), 5, getLocaleMessage("report.productMadeCurrency"), styleHaeder);
        createCell(rows.get(1), 6, getLocaleMessage("report.brakMadeCurrency"), styleHaeder);
        createCell(rows.get(1), 7, getLocaleMessage("report.workerMadeCurrency"), styleHaeder);
        createCell(rows.get(1), 8, getLocaleMessage("report.createDate"), styleHaeder);


        createCell(rows.get(1), 10, getLocaleMessage("report.name.worker"), styleHaeder);
        createCell(rows.get(1), 11, getLocaleMessage("report.product"), styleHaeder);
        createCell(rows.get(1), 12, getLocaleMessage("report.brak.workers"), styleHaeder);
        createCell(rows.get(1), 13, getLocaleMessage("report.made.product.currency"), styleHaeder);
        createCell(rows.get(1), 14, getLocaleMessage("report.made.brak.currency"), styleHaeder);
        createCell(rows.get(1), 15, getLocaleMessage("report.workers.currency"), styleHaeder);
    }

    private void writeTransactionDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(8);
        style.setFont(font);

        salaryDto.getSalaryDetailInfoDtoList().forEach(tr -> {
            int columnNum = 0;
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum, tr.getProductName(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 1, tr.getProductType().toString(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 2, tr.getWorkerName().toString(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 3, tr.getProduct(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 4, tr.getWorkerBrak(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 5, tr.getProductMadeCurrency(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 6, tr.getBrakMadeCurrency(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 7, tr.getWorkerMadeCurrency(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryDetailInfoDtoList().indexOf(tr)), columnNum + 8, tr.getCreateDate().toString(), style);

        });

       salaryDto.getSalaryInfoDtoList().forEach(tr -> {
            int columnNum = 10;
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum, tr.getWorkerName(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum + 1, tr.getProduct().toString(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum + 2, tr.getWorkerBrak().toString(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum + 3, tr.getProductMadeCurrency(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum + 4, tr.getBrakMadeCurrency(), style);
            createCell(this.rows.get(2 + salaryDto.getSalaryInfoDtoList().indexOf(tr)), columnNum + 5, tr.getWorkerMadeCurrency(), style);

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
