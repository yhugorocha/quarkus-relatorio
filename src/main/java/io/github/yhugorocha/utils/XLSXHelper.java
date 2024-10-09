package io.github.yhugorocha.utils;

import io.github.yhugorocha.dto.OpportunityDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class XLSXHelper{

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    public ByteArrayOutputStream exportToXLSX(List<OpportunityDTO> data, String[] headers) {

        // Criando a planilha
        try(XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("PLANILHA DE QUOTATION");

            // Criando cabeçalho
            createHeadersWorkbook(workbook, sheet, headers);

            addDataOpportunity(sheet, data);

            // Ajustando automaticamente a largura de todas as colunas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Convertendo a planilha para um array de bytes
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);

            return out;

        }catch (IOException  e){
           throw new RuntimeException("Não foi possivel preencher planilha");
        }
    }

    private static void createHeadersWorkbook(XSSFWorkbook workbook, XSSFSheet sheet, String[] headers){

        //linha do header
        Row headerRow = sheet.createRow(0);

        // Estilo para o cabeçalho
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true); // Negrito no cabeçalho
        headerStyle.setFont(font);

        // Adicionando as células do cabeçalho
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle); // Aplicando o estilo
        }
    }

    private void addDataOpportunity(Sheet sheet, List<OpportunityDTO> data) {
        int rowNum = 1; // Começa na segunda linha, pois a primeira é o cabeçalho
        for (OpportunityDTO opportunity : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(opportunity.proposalId().toString());
            row.createCell(1).setCellValue(opportunity.customer());
            row.createCell(2).setCellValue(opportunity.priceTonne().toString());
            row.createCell(3).setCellValue(opportunity.lastDollarQuotation().toString());
        }
    }
}
