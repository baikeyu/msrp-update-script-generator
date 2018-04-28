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
nst2priceMap.put("21314810-AAA1", "474700");
nst2priceMap.put("21314810-AAA", "474700");
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
        println nstStartPriceBefore + "->" + nstStartPriceafter
    } else {
        println "Can't find nst code"+ key;
        continue
    }



}



