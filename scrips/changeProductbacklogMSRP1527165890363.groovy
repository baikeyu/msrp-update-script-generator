import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel;
import com.daimler.dcp.biz.china.enums.DcpProductImportStatus;
flexibleSearchService = spring.getBean "flexibleSearchService"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> vin2priceMap = new HashMap();
//start retail msrp definition
vin2priceMap.put("WMEFJ4DA0JK278034", "126800");
vin2priceMap.put("WMEFJ4DA0JK278194", "136888");
vin2priceMap.put("WMEFJ4DA1JK278429", "116800");
vin2priceMap.put("WMEFJ4DA1JK279967", "136888");
vin2priceMap.put("WMEFJ4DA2JK281789", "126800");
vin2priceMap.put("WMEFJ4DA3JK280991", "136888");
vin2priceMap.put("WMEFJ4DA3JK281459", "126800");
vin2priceMap.put("WMEFJ4DA4JK273774", "116800");
vin2priceMap.put("WMEFJ4DA5JK272326", "116800");
vin2priceMap.put("WMEFJ4DA5JK278367", "126800");
vin2priceMap.put("WMEFJ4DA5JK279762", "136888");
vin2priceMap.put("WMEFJ4DA6JK270326", "116800");
vin2priceMap.put("WMEFJ4DA6JK273758", "116800");
vin2priceMap.put("WMEFJ4DA6JK274408", "116800");
vin2priceMap.put("WMEFJ4DA6JK277020", "126800");
vin2priceMap.put("WMEFJ4DA7JK274238", "116800");
vin2priceMap.put("WMEFJ4DA8JK269291", "116800");
vin2priceMap.put("WMEFJ4DA9JK278761", "136888");
vin2priceMap.put("WMEFJ4DAXJK281765", "126800");
vin2priceMap.put("WMEFJ5DA1JK262947", "153888");
vin2priceMap.put("WMEFJ5DA1JK263080", "153888");
vin2priceMap.put("WMEFJ5DA4JK263056", "153888");
vin2priceMap.put("WMEFJ5DA5JK262496", "153888");
vin2priceMap.put("WMEFJ5DA5JK263020", "153888");
vin2priceMap.put("WMEFJ5DA5JK278505", "155888");
vin2priceMap.put("WMEFJ5DA7JK280353", "155888");
vin2priceMap.put("WMEFJ5DA8JK263030", "153888");
vin2priceMap.put("WMEFJ5DA8JK263593", "153888");
vin2priceMap.put("WMEFJ5DA8JK278997", "155888");
vin2priceMap.put("WMEFJ5DA9JK262971", "153888");
vin2priceMap.put("WMEFJ5DA9JK279284", "155888");
vin2priceMap.put("WMEFJ5DAXJK262865", "153888");
vin2priceMap.put("WMEFJ5DAXJK278967", "155888");
vin2priceMap.put("WMEFJ6CA2JK242245", "212800");
vin2priceMap.put("WMEFJ6CA2JK243167", "212800");
vin2priceMap.put("WMEFJ6CA5JK241512", "212800");
vin2priceMap.put("WMEFJ6CA5JK241946", "212800");


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



