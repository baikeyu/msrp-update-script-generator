import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel;
import com.daimler.dcp.biz.china.enums.DcpProductImportStatus;
flexibleSearchService = spring.getBean "flexibleSearchService"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> vin2priceMap = new HashMap();
//put all start price here
vin2priceMap.put("YYYANNCNSS4000145", "163000");
vin2priceMap.put("LE4ZG4JB7HL095896", "474700");

for (key in vin2priceMap.keySet()) {

	//DcpProductImportBacklog

	final DcpProductImportBacklogModel backlogEx = new DcpProductImportBacklogModel()
	backlogEx.setVin(key);
	final DcpProductImportBacklogModel backlog = null;
	try {
		backlog = flexibleSearchService.getModelByExample(backlogEx);
	} catch(ModelNotFoundException e) {
		println "Can't find vin"+ key;
		continue
	}

	final String nstStartPriceBefore = backlog.getTotalPrice();

	backlog.setTotalPrice(vin2priceMap.get(key));
	backlog.setStatus(DcpProductImportStatus.REIMPORT)
	modelService.saveAll();

	final String nstStartPriceafter = backlog.getTotalPrice();

	println nstStartPriceBefore + "->" + nstStartPriceafter

}



