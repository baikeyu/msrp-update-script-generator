import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel;
import com.daimler.dcp.biz.china.enums.DcpProductImportStatus;
flexibleSearchService = spring.getBean "flexibleSearchService"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> vin2priceMap = new HashMap();
//start retail msrp definition
vin2priceMap.put("WMEFJ4DA0JK278194", "145888");
vin2priceMap.put("WMEFJ4DA1JK279967", "145888");
vin2priceMap.put("WMEFJ4DA3JK280991", "145888");
vin2priceMap.put("WMEFJ4DA5JK279762", "145888");
vin2priceMap.put("WMEFJ4DA9JK278761", "145888");
vin2priceMap.put("WMEFJ5DA5JK278505", "165888");
vin2priceMap.put("WMEFJ5DA7JK280353", "165888");
vin2priceMap.put("WMEFJ5DA8JK278997", "165888");
vin2priceMap.put("WMEFJ5DA9JK279284", "165888");
vin2priceMap.put("WMEFJ5DAXJK278967", "165888");
vin2priceMap.put("WMEFJ6CA2JK242245", "227000");
vin2priceMap.put("WMEFJ6CA2JK243167", "227000");
vin2priceMap.put("WMEFJ6CA5JK241946", "227000");
vin2priceMap.put("WMEFJ6CA5JK242854", "227000");
vin2priceMap.put("WMEFJ6CA5JK241512", "227000");


//end retail msrp definition

for (key in vin2priceMap.keySet()) {

	//DcpProductImportBacklog

	final DcpProductImportBacklogModel backlogEx = new DcpProductImportBacklogModel()
	backlogEx.setVin(key);
	final DcpProductImportBacklogModel backlog = null;
	try {
		backlog = flexibleSearchService.getModelByExample(backlogEx);
	} catch(ModelNotFoundException e) {
		println "Can't find vin "+ key;
		continue
	}

	final String nstStartPriceBefore = backlog.getTotalPrice();

	backlog.setTotalPrice(vin2priceMap.get(key));
	backlog.setStatus(DcpProductImportStatus.REIMPORT)
	modelService.saveAll();

	final String nstStartPriceafter = backlog.getTotalPrice();

	println key + "|" + nstStartPriceBefore + "|" + nstStartPriceafter

}
