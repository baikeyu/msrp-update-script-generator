import com.daimler.dcp.biz.china.model.DcpSimplePriceModel
import com.daimler.dcp.biz.china.model.DcpVehicleNstModel
import de.hybris.platform.core.model.c2l.CurrencyModel

import java.math.BigDecimal
import java.util.ArrayList
import java.util.List

dcpVehicleNstDao = spring.getBean "dcpVehicleNstDao"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> nst2priceMap = new HashMap();
//put all start price here
nst2priceMap.put("46323410-CN7", "1568800");
nst2priceMap.put("46323410-CN8", "1698800");
nst2priceMap.put("46323410-X71", "1566800");
nst2priceMap.put("46323410-X81", "1696800");
nst2priceMap.put("46327210-CN3", "2146800");
nst2priceMap.put("46327210-CN8", "2398800");
nst2priceMap.put("46327410-CN8", "3532800");
nst2priceMap.put("46334810-CN3", "1228800");
nst2priceMap.put("46334810-X31", "1226800");
nst2priceMap.put("20743610-CN6", "669800");
nst2priceMap.put("453443-CN3", "153800");
nst2priceMap.put("453443-CN4", "153800");
nst2priceMap.put("453453-CN5", "191800");
nst2priceMap.put("453453-CN6", "173800");
nst2priceMap.put("453462-CN3", "308888");
nst2priceMap.put("11734210-CN8", "232800");
nst2priceMap.put("11734310-CN6", "250800");
nst2priceMap.put("11734310-CN7", "269888");
nst2priceMap.put("11734310-CN8", "256800");
nst2priceMap.put("11734310-CN9", "274800");
nst2priceMap.put("11734610-CN2", "352800");
nst2priceMap.put("11734610-CN8", "353800");
nst2priceMap.put("11734710-CN2", "298800");
nst2priceMap.put("11734710-CN8", "298800");
nst2priceMap.put("11734710-CN9", "306800");
nst2priceMap.put("11734710-X81", "318800");
nst2priceMap.put("11735210-CN2", "557800");
nst2priceMap.put("11735210-CN8", "558800");
nst2priceMap.put("11735210-CN9", "558800");
nst2priceMap.put("11735210-X81", "591800");
nst2priceMap.put("11734210-CN9", "237800");
nst2priceMap.put("11734610-CN9", "359800");
nst2priceMap.put("19037710-CN1", "1321800");
nst2priceMap.put("19037810-CN1", "1573800");
nst2priceMap.put("19037810-CN2", "1595800");
nst2priceMap.put("19037810-CN3", "1835800");
nst2priceMap.put("19037910-CN1", "2133800");
nst2priceMap.put("19037910-CN2", "2155800");
nst2priceMap.put("19037910-X11", "2085800");
nst2priceMap.put("19038010-CN1", "1858800");
nst2priceMap.put("20534210-CN1", "357800");
nst2priceMap.put("20534210-CN2", "357800");
nst2priceMap.put("20534210-CN3", "358800");
nst2priceMap.put("20534310-CN1", "395800");
nst2priceMap.put("20534310-CN2", "396800");
nst2priceMap.put("20534810-CN1", "455800");
nst2priceMap.put("20534810-CN2", "455800");
nst2priceMap.put("20534810-CN3", "468800");
nst2priceMap.put("20538610-CN2", "958800");
nst2priceMap.put("20538610-CN3", "958800");
nst2priceMap.put("20538710-CN2", "1195800");
nst2priceMap.put("20538710-CN3", "1195800");
nst2priceMap.put("20733410-CN6", "495800");
nst2priceMap.put("20736510-CN6", "692800");
nst2priceMap.put("21736410-CN2", "1218800");
nst2priceMap.put("21736410-X21", "1273800");
nst2priceMap.put("21737810-CN3", "2323800");
nst2priceMap.put("21738410-CN3", "1858800");
nst2priceMap.put("21833610-CN8", "648800");
nst2priceMap.put("21836210-CN8", "712800");
nst2priceMap.put("21836710-CN8", "918800");
nst2priceMap.put("21839210-CN8", "1651800");
nst2priceMap.put("23834210-CN1", "522800");
nst2priceMap.put("23834210-CN2", "522800");
nst2priceMap.put("23834310-CN1", "548800");
nst2priceMap.put("23834310-CN2", "548800");
nst2priceMap.put("23834810-CN1", "598800");
nst2priceMap.put("23834810-CN2", "598800");
nst2priceMap.put("25334310-CN1", "463800");
nst2priceMap.put("25334310-CN2", "463800");
nst2priceMap.put("25334310-CN3", "463800");
nst2priceMap.put("25334610-CN1", "498800");
nst2priceMap.put("25334610-CN2", "498800");
nst2priceMap.put("25334610-CN3", "498800");
nst2priceMap.put("25334610-X21", "497800");
nst2priceMap.put("25334910-CN1", "594800");
nst2priceMap.put("25334910-CN2", "594800");
nst2priceMap.put("25334910-CN3", "594800");
nst2priceMap.put("25336410-CN1", "679800");
nst2priceMap.put("25336410-CN2", "679800");
nst2priceMap.put("25336410-X11", "751800");
nst2priceMap.put("25336410-X12", "679800");
nst2priceMap.put("25336410-X13", "751800");
nst2priceMap.put("29235610-CN2", "895800");
nst2priceMap.put("29235610-CN3", "895800");
nst2priceMap.put("29235610-CN5", "895800");
nst2priceMap.put("29236210-CN2", "804800");
nst2priceMap.put("29236210-CN3", "804800");
nst2priceMap.put("29236210-CN5", "804800");
nst2priceMap.put("29236410-CN3", "989800");
nst2priceMap.put("29236410-CN5", "1010800");
nst2priceMap.put("29237110-CN1", "1389800");
nst2priceMap.put("29237110-CN2", "1389800");
nst2priceMap.put("29237410-CN2", "1798800");
nst2priceMap.put("29237410-CN3", "1798800");
nst2priceMap.put("453343-CN2", "126800");
nst2priceMap.put("453343-CN3", "116800");
nst2priceMap.put("453343-CN9", "136888");
nst2priceMap.put("453343-CNA", "137888");
nst2priceMap.put("453343-CNC", "126800");
nst2priceMap.put("453343-CND", "116800");
nst2priceMap.put("453343-X21", "136888");
nst2priceMap.put("453353-CN2", "145800");
nst2priceMap.put("453353-CN3", "163800");
nst2priceMap.put("453353-CN4", "163800");
nst2priceMap.put("453353-CN5", "146888");
nst2priceMap.put("453353-CN6", "146888");
nst2priceMap.put("453353-CN7", "145800");
nst2priceMap.put("453353-CNA", "175888");
nst2priceMap.put("453353-X21", "163888");
nst2priceMap.put("453362-CN2", "212800");
nst2priceMap.put("453362-CN3", "279888");
nst2priceMap.put("453362-CN4", "212800");
nst2priceMap.put("17243410-CN1", "518800");
nst2priceMap.put("17243410-CN2", "552800");
nst2priceMap.put("17243510-CN1", "518800");
nst2priceMap.put("17243510-CN2", "545800");
nst2priceMap.put("17243810-CN1", "638800");
nst2priceMap.put("17243810-CN2", "638800");
nst2priceMap.put("23146610-CN1", "1016800");
nst2priceMap.put("23146610-CN2", "1016800");
nst2priceMap.put("23146610-X21", "1116800");
nst2priceMap.put("17604210-CN7", "219800");
nst2priceMap.put("17604210-CN8", "219800");
nst2priceMap.put("17604310-CN4", "278800");
nst2priceMap.put("17604310-CNA", "238800");
nst2priceMap.put("17604310-CNB", "263800");
nst2priceMap.put("17604310-CND", "238800");
nst2priceMap.put("17604310-CNE", "263800");
nst2priceMap.put("17604310-CNF", "278800");
nst2priceMap.put("17605210-CN6", "463800");
nst2priceMap.put("17605210-CN8", "463800");
nst2priceMap.put("17605210-CN9", "463800");
nst2priceMap.put("17605210-X81", "521800");
nst2priceMap.put("17604410-CN6", "336800");
nst2priceMap.put("17604410-CN9", "336800");
nst2priceMap.put("17604410-CN8", "336800");
nst2priceMap.put("20524010-CN3", "358800");
nst2priceMap.put("20524210-CN1", "387800");
nst2priceMap.put("20524210-CN2", "387800");
nst2priceMap.put("20524210-CN3", "388800");
nst2priceMap.put("20524310-CN1", "452800");
nst2priceMap.put("20524810-CN1", "528800");
nst2priceMap.put("20524310-CN3", "452800");
nst2priceMap.put("24624210-CN2", "225800");
nst2priceMap.put("24624210-CN8", "225800");
nst2priceMap.put("24624210-CN9", "225800");
nst2priceMap.put("24624310-CNA", "243800");
nst2priceMap.put("24624310-CNB", "262800");
nst2priceMap.put("24624310-CNC", "283800");
nst2priceMap.put("24624310-CND", "243800");
nst2priceMap.put("24624310-CNE", "262800");
nst2priceMap.put("24624310-CNF", "283800");
nst2priceMap.put("24624310-CNG", "243800");
nst2priceMap.put("24624310-CNH", "262800");
nst2priceMap.put("24624310-CNI", "283800");
nst2priceMap.put("24624310-XB1", "272800");
nst2priceMap.put("24624310-XE1", "283800");
nst2priceMap.put("24624410-CN2", "339800");
nst2priceMap.put("24624410-CN8", "339800");
nst2priceMap.put("24624410-CN9", "339800");
nst2priceMap.put("22215510-CN1", "1028800");
nst2priceMap.put("22216210-CN8", "876800");
nst2priceMap.put("22216210-CNA", "876800");
nst2priceMap.put("22216210-XA1", "876800");
nst2priceMap.put("22216310-CN3", "1636800");
nst2priceMap.put("22216410-CN1", "1228800");
nst2priceMap.put("22216410-CN2", "1228800");
nst2priceMap.put("22216510-CN3", "1158800");
nst2priceMap.put("22216610-CN1", "1139800");
nst2priceMap.put("22216710-CN3", "1268800");
nst2priceMap.put("22216910-CN1", "1732800");
nst2priceMap.put("22217810-CN3", "2328800");
nst2priceMap.put("22217910-CN3", "2795800");
nst2priceMap.put("22217910-CN5", "2795800");
nst2priceMap.put("22218410-CN3", "1862800");
nst2priceMap.put("22218810-CN1", "2328800");
nst2priceMap.put("25116310-CN3", "548800");
nst2priceMap.put("25116310-CN4", "632800");
nst2priceMap.put("25116610-CN3", "726800");
nst2priceMap.put("16600410-CN2", "711800");
nst2priceMap.put("16600410-CN3", "711800");
nst2priceMap.put("16605610-CN2", "862800");
nst2priceMap.put("16605610-CN3", "862800");
nst2priceMap.put("16606210-CN2", "719800");
nst2priceMap.put("16606210-CN3", "762800");
nst2priceMap.put("16606210-CN7", "719800");
nst2priceMap.put("16606210-CN9", "762800");
nst2priceMap.put("16606310-CN6", "1099800");
nst2priceMap.put("16606310-CN7", "1099800");
nst2priceMap.put("16606410-CN3", "965800");
nst2priceMap.put("16607410-CN3", "1728800");
nst2priceMap.put("16607410-CN6", "1728800");
nst2priceMap.put("16607510-CN3", "1848800");
nst2priceMap.put("16607510-CN6", "1848800");
nst2priceMap.put("20506410-CN1", "639800");
nst2priceMap.put("20508610-CN3", "928800");
nst2priceMap.put("20508710-CN2", "1165800");
nst2priceMap.put("20508710-CN3", "1165800");
nst2priceMap.put("20508710-X21", "1208800");
nst2priceMap.put("21306410-CN1", "918800");
nst2priceMap.put("21308910-CN1", "1668800");
nst2priceMap.put("453043-CN2", "135800");
nst2priceMap.put("453043-CN3", "125800");
nst2priceMap.put("453043-CN4", "135800");
nst2priceMap.put("453043-CN5", "125800");
nst2priceMap.put("453053-CN2", "154800");
nst2priceMap.put("453053-CN4", "173800");
nst2priceMap.put("453053-CN9", "154800");
nst2priceMap.put("453062-CN2", "220800");
nst2priceMap.put("15695210-CN2", "537800");
nst2priceMap.put("15695210-CN3", "538800");
nst2priceMap.put("15695210-CN5", "538800");
nst2priceMap.put("15695210-X31", "589800");
nst2priceMap.put("16682410-CN3", "996800");
nst2priceMap.put("16685610-CN3", "1073800");
nst2priceMap.put("16685610-CN6", "1245800");
nst2priceMap.put("16685610-CN7", "1003800");
nst2priceMap.put("16685610-CN8", "1073800");
nst2priceMap.put("16685610-CN9", "1245800");
nst2priceMap.put("16685610-CNA", "1075800");
nst2priceMap.put("16685610-CNB", "1249800");
nst2priceMap.put("16686210-CN1", "955800");
nst2priceMap.put("16686210-CN2", "956800");
nst2priceMap.put("16686210-X11", "961800");
nst2priceMap.put("16687110-CN2", "1480800");
nst2priceMap.put("16687110-CN3", "1480800");
nst2priceMap.put("16687110-CN4", "1481800");
nst2priceMap.put("16687510-CN2", "1846800");
nst2priceMap.put("16687510-CN3", "1846800");
nst2priceMap.put("22296410-CN1", "1398800");
nst2priceMap.put("22296410-X12", "1419000");
nst2priceMap.put("22296710-CN3", "1369800");
nst2priceMap.put("22298010-CN1", "2888800");
nst2priceMap.put("22298610-CN1", "2138800");
nst2priceMap.put("25396410-CN1", "651800");
nst2priceMap.put("25396410-CN2", "651800");
nst2priceMap.put("25396410-X11", "722800");
nst2priceMap.put("25396410-X12", "651800");
nst2priceMap.put("25396410-X13", "650800");
nst2priceMap.put("25396410-X14", "722800");
nst2priceMap.put("17604210-CN9", "219800");
nst2priceMap.put("17604310-CN2", "238800");
nst2priceMap.put("17604310-CN3", "263800");
nst2priceMap.put("17604310-CNC", "278800");
nst2priceMap.put("17604310-XB1", "293800");
nst2priceMap.put("19037710-CN2", "1321800");
nst2priceMap.put("20534310-CN4", "395800");
nst2priceMap.put("20538710-X21", "1258800");
nst2priceMap.put("20524010-CN1", "357800");
nst2priceMap.put("20524010-CN2", "357800");
nst2priceMap.put("20524310-CN2", "452800");
nst2priceMap.put("20524810-CN2", "528800");
nst2priceMap.put("20524810-CN3", "528800");
nst2priceMap.put("20508610-CN2", "928800");
nst2priceMap.put("11734210-CN1", "229800");
nst2priceMap.put("11734310-CN3", "248800");
nst2priceMap.put("11734310-CN5", "267888");
nst2priceMap.put("21837610-CN8", "1735800");
nst2priceMap.put("21893610-CN7", "668800");
nst2priceMap.put("21896710-CN7", "918800");
nst2priceMap.put("20746510-CN6", "793800");
nst2priceMap.put("20733610-CN6", "559800");
nst2priceMap.put("20736210-CN6", "646800");
nst2priceMap.put("46323413-CN2", "1661800");
nst2priceMap.put("46323413-CN3", "1568800");
nst2priceMap.put("46327210-X31", "2143800");
nst2priceMap.put("46327210-X81", "2393800");
nst2priceMap.put("46327213-CN1", "2146800");
nst2priceMap.put("46327213-CN2", "2313800");
nst2priceMap.put("46327410-X81", "3527800");
nst2priceMap.put("46327413-CN1", "3532800");
nst2priceMap.put("46334813-CN1", "1228800");
nst2priceMap.put("46334813-CN2", "1206800");
nst2priceMap.put("25334310-X21", "463800");
nst2priceMap.put("29236410-CN2", "989800");
nst2priceMap.put("29236410-CN6", "989800");
nst2priceMap.put("29237110-CN3", "1389800");
nst2priceMap.put("29237410-CN5", "1798800");
nst2priceMap.put("16602410-CN2", "795800");
nst2priceMap.put("16602410-CN3", "795800");
nst2priceMap.put("16606410-CN2", "965800");
nst2priceMap.put("16682410-CN2", "996800");
nst2priceMap.put("16682410-CN4", "998800");
nst2priceMap.put("16687510-CN4", "1846800");
nst2priceMap.put("22216210-CN9", "1028800");
nst2priceMap.put("22296410-X11", "1398800");
nst2priceMap.put("22297610-CN3", "2698800");
nst2priceMap.put("22298410-CN2", "2053800");
nst2priceMap.put("453453-CN3", "173800");
nst2priceMap.put("453453-CN4", "191800");
nst2priceMap.put("453462-CN4", "308888");
nst2priceMap.put("453343-CNB", "130888");
nst2priceMap.put("453362-CN5", "279888");
nst2priceMap.put("453343-CN6", "130888");
nst2priceMap.put("453343-CN7", "131888");
nst2priceMap.put("453353-X22", "153888");
nst2priceMap.put("453053-CN3", "173800");
nst2priceMap.put("453062-CN3", "220800");
nst2priceMap.put("453053-CN6", "149888");
nst2priceMap.put("11734310-X71", "267888");
nst2priceMap.put("21736410-CN3", "1243800");
nst2priceMap.put("453343-CNE", "136888");
nst2priceMap.put("453353-CN8", "155888");
nst2priceMap.put("453362-CN6", "217888");
nst2priceMap.put("453053-CN7", "148800");
nst2priceMap.put("24624310-XI1", "285800");
nst2priceMap.put("23844310-CN1", "662800");
nst2priceMap.put("16600410-CN4", "711800");
nst2priceMap.put("16602410-CN4", "795800");
nst2priceMap.put("16605610-CN4", "862800");
nst2priceMap.put("16606210-CNA", "719800");
nst2priceMap.put("16606210-CNB", "762800");
nst2priceMap.put("16606410-CN4", "965800");
nst2priceMap.put("16607410-CN7", "1728800");
nst2priceMap.put("16607510-CN7", "1848800");
nst2priceMap.put("23844210-CN1", "642800");
nst2priceMap.put("23844210-CN2", "658800");
nst2priceMap.put("23844310-X11", "660800");

//end start price define

CurrencyModel currency = currencyDao.findCurrenciesByCode("CNY").get(0);

for (key in nst2priceMap.keySet()) {
    final DcpVehicleNstModel nstModel = dcpVehicleNstDao.findDcpVehicleNstByCode(key);

    if (nstModel != null) {

        final BigDecimal nstStartPriceBefore = nstModel.getStartPrice().get(0).getPrice();

        final DcpSimplePriceModel price = new DcpSimplePriceModel();
        final List<DcpSimplePriceModel> priceList = new ArrayList<>();

        price.setPrice(new BigDecimal(nst2priceMap.get(key)));
        price.setCurrency(currency);
        priceList.add(price);
        nstModel.setStartPrice(priceList);

        modelService.saveAll();

        final BigDecimal nstStartPriceafter = nstModel.getStartPrice().get(0).getPrice();
        println key + "|" + nstStartPriceBefore + "|" + nstStartPriceafter
    } else {
        println "Can't find nst code "+ key;
        continue
    }



}



