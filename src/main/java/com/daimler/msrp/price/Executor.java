package com.daimler.msrp.price;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Executor {

    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        /**
         getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
         */
        //URL url = classLoader.getResource("20180502_smart_vin.xlsx");
        //URL url = classLoader.getResource("20180524.xlsx");
        //URL url = classLoader.getResource("20180524_unclaim_vins.xlsx");
        URL url = classLoader.getResource("20180524_smart_vinpool_reload.xlsx");
        /**
         * url.getFile() 得到这个文件的绝对路径
         */
        System.out.println(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file.exists());
        //List<PriceModel> priceList = ExcelParser.getStandardMsrpList(file);
        List<RetailPriceModel> priceList = ExcelParser.getRetailMsrpList(file);
        //List<VinModel> vinList = ExcelParser.getVinList(file);

        String timestamp = String.valueOf(System.currentTimeMillis());
        //ScriptGenerator.generateScript("changeStartPrice" + timestamp + ".groovy", priceList, ScriptType.NST_MSRP);
        ScriptGenerator.generateScript("changeProductbacklogMSRP" + timestamp + ".groovy", priceList, ScriptType.RETAIL_MSRP);
        //ScriptGenerator.generateScript("unclaimVin" + timestamp + ".groovy", vinList, ScriptType.UNCLAIM);

    }
}
