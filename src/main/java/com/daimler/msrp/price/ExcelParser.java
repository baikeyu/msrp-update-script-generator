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

public class ExcelParser {

    private static final Integer[] CBU_POS = {3, 7, 6};
    private static final Integer[] PBP_POS = {1, 5, 6};

    private static final Integer[] SMART_RETAIL_POS = {1, 2};
    private static final Integer[] UNCLAIM_VIN_POS = {0, 2};

    private static final Integer[] RETAIL_PRICE_POS = {0, 8};

    private static final String TARGET_LIST = "15694310, 25394310, 21314210, 20504310, 21314310, 20504210, 21304210, 21304310, 25394910, 15694710, 25394610, 21304810, 20514010, 20514210, 20514310, 21314810";

    public static void searchString(File file) {
        Workbook workbook = getWorkbook(file);
        for (Sheet sheet : workbook){
            System.out.println("Read excel sheet: " + sheet.getSheetName());
            sheetSearch(sheet, TARGET_LIST);
        }
    }

    public static List<RetailPriceModel> getRetailMsrpList(File file) {
        Workbook workbook = getWorkbook(file);
        List<RetailPriceModel> priceList = new ArrayList<RetailPriceModel>();
        for (Sheet sheet : workbook){
            System.out.println("Read excel sheet: " + sheet.getSheetName());
            priceList.addAll(createRetailPriceModel(sheet, SMART_RETAIL_POS));
        }
        return priceList;
    }



    public static List<VinModel> getVinList(File file) {
        Workbook workbook = getWorkbook(file);
        List<VinModel> vinList = new ArrayList<VinModel>();
        for (Sheet sheet : workbook){
            System.out.println("Read excel sheet: " + sheet.getSheetName());
            vinList.addAll(createVinList(sheet, UNCLAIM_VIN_POS));
        }
        return vinList;
    }

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

    private static void sheetSearch(Sheet sheet, String list) {
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Cell baumusterCell = row.getCell(2);
            String baumuste = baumusterCell != null ? baumusterCell.getStringCellValue() : "null";
            if (list.contains(baumuste)) {
                String nst = row.getCell(3).getStringCellValue();
                System.out.println(nst);
            }
        }
    }

    private static List<RetailPriceModel> createRetailPriceModel(Sheet sheet, Integer ... pos) {
        List<RetailPriceModel> retailPriceList = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Cell vinCell = row.getCell(pos[0]);
            Cell newPriceCel = row.getCell(pos[1]);
            String vin = vinCell != null ? formatNstCode(vinCell.getStringCellValue()) : "";
            String newPrice = newPriceCel != null ? newPriceCel.getStringCellValue().replaceAll(",", "")
                    .replaceAll("\"", "") : "";
            RetailPriceModel vinModel = new RetailPriceModel(vin, newPrice);
            System.out.println(vinModel);
            retailPriceList.add(vinModel);
        }
        return retailPriceList;
    }

    private static List<VinModel> createVinList(Sheet sheet, Integer ... pos) {
        List<VinModel> vinList = new ArrayList<VinModel>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Cell vinCell = row.getCell(pos[0]);
            Cell dealerCell = row.getCell(pos[1]);
            String vin = vinCell != null ? formatNstCode(vinCell.getStringCellValue()) : "";
            String dealer = dealerCell != null ? dealerCell.getStringCellValue().replaceAll(",", "") : "";
            VinModel vinModel = new VinModel(vin, dealer);
            System.out.println(vinModel);
            vinList.add(vinModel);
        }
        return vinList;
    }


    private static List<PriceModel> createPriceModel(Sheet sheet, Integer ... pos) {
        List<PriceModel> priceList = new ArrayList<PriceModel>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Cell nstCell = row.getCell(pos[0]);
            Cell originalPriceCell = row.getCell(pos[1]);
            Cell newPriceCell = row.getCell(pos[2]);
            String nst = nstCell != null ? formatNstCode(nstCell.getStringCellValue()) : "";
            String originalPrice = originalPriceCell != null ? originalPriceCell.getStringCellValue().replaceAll(",", "") : "";
            String newPrice = newPriceCell != null ? newPriceCell.getStringCellValue().replaceAll(",", "") : "";
            PriceModel priceModel = new PriceModel(nst, originalPrice, newPrice);
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

    private static String formatNstCode(String nst) {
        if (nst.startsWith("453")) {
            String smartBaumaster = nst.substring(0, 6);
            String smartNst = nst.substring(8);
            return smartBaumaster + smartNst;
        }
        return nst;
    }
}
