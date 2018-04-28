package com.daimler.msrp.price;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelPaser {

    private static final Integer[] CBU_POS = {3, 7, 8};
    private static final Integer[] PBP_POS = {1, 5, 6};

    public static List<PriceModel> getStandardMsrpList(File file) {
        Workbook workbook = getWorkbook(file);
        List<PriceModel> priceList = new ArrayList<PriceModel>();
        for (Sheet sheet : workbook){
            System.out.println("Read excel sheet: " + sheet.getSheetName());
            if ("CBU".equals(sheet.getSheetName())) {
                priceList.addAll(createPriceModel(sheet, CBU_POS));
            } else if ("PbP".equals(sheet.getSheetName())) {
                priceList.addAll(createPriceModel(sheet, PBP_POS));
            }
        }
        return priceList;
    }

    private static List<PriceModel> createPriceModel(Sheet sheet, Integer ... pos) {
        List<PriceModel> priceList = new ArrayList<PriceModel>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Cell nst = row.getCell(pos[0]);
            Cell originalPrice = row.getCell(pos[1]);
            Cell newPrice = row.getCell(pos[2]);
            PriceModel priceModel = new PriceModel(nst.getStringCellValue(), originalPrice.getStringCellValue(),
                    newPrice.getStringCellValue());
            System.out.println(priceModel);
            priceList.add(priceModel);
        }
        return priceList;
    }

    private static Workbook getWorkbook(File file) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);            // InputStream or File for XLSX file (required)
        return workbook;
    }
}
