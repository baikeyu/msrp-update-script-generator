package com.daimler.msrp.price;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class ScriptGenerator {

    private static final String NST_MSRP = "nst2priceMap.put(\"%s\", \"%s\");";
    private static final String RETAIL_MSRP = "vin2priceMap.put(\"%s\", \"%s\");";
    private static final String UNCLAIM_MAPPING = "vinMap.put(\"%s\", \"%s\");";

    private static final String NST_TEMPLATE_NAME = "changeStartPrice.template";
    private static final String RETAIL_TEMPLATE_NAME = "changeProductbacklogMSRP.template";
    private static final String UNCLAIM_TEMPLATE_NAME = "unclaimVin.template";

    public static void generateScript(String fileName, List list, ScriptType type) {
        String updateContent;
        switch (type) {
            case NST_MSRP:
                File nstTemplateFile = new File(ClassLoader.getSystemResource(NST_TEMPLATE_NAME).getFile());
                updateContent = convertPriceInfo(list, NST_MSRP);
                writeToFile(fileName, readScriptTemplate(nstTemplateFile, updateContent));
                break;
            case RETAIL_MSRP:
                File retailTemplateFile = new File(ClassLoader.getSystemResource(RETAIL_TEMPLATE_NAME).getFile());
                updateContent = convertPriceInfo(list, RETAIL_MSRP);
                writeToFile(fileName, readScriptTemplate(retailTemplateFile, updateContent));
                break;
            case UNCLAIM:
                File unclaimTemplateFile = new File(ClassLoader.getSystemResource(UNCLAIM_TEMPLATE_NAME).getFile());
                updateContent = convertUnclaimVin(list, UNCLAIM_MAPPING);
                writeToFile(fileName, readScriptTemplate(unclaimTemplateFile, updateContent));
                break;
            default:
                break;
        }
    }

    private static void writeToFile(String fileName, String content) {
        System.out.println(content);
        try {
            File targetFile = new File("scrips/" + fileName);
            FileUtils.touch(targetFile);
            FileUtils.writeStringToFile(targetFile, content, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String convertPriceInfo(List<PriceModel> priceList, String template) {
        StringBuilder sb = new StringBuilder();
        for (PriceModel price : priceList) {
            sb.append(String.format(template, price.getId(), price.getChangePrice()) + "\n");
        }
        return sb.toString();
    }

    private static String convertUnclaimVin(List<VinModel> vinList, String template) {
        StringBuilder sb = new StringBuilder();
        for (VinModel vin : vinList) {
            sb.append(String.format(template, vin.getVin(), vin.getDealerCode()) + "\n");
        }
        return sb.toString();
    }

    private static String readScriptTemplate(File file, String replaceString) {
        BufferedReader reader = null;
        final StringBuilder newContent = new StringBuilder();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            //Reading all the lines of input text file into oldContent
            reader.lines().forEach(s -> newContent.append(String.format(s, replaceString)).append("\n"));
            //Rewriting the input text file with newContent
            System.out.println(newContent.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return newContent.toString();
    }
}
