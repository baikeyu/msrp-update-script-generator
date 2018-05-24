import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel;
import com.daimler.dcp.biz.china.enums.DcpProductImportStatus;
flexibleSearchService = spring.getBean "flexibleSearchService"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> vin2priceMap = new HashMap();
//start retail msrp definition
vin2priceMap.put("WDCDA6CB6JB120674", "730800");
vin2priceMap.put("WDCDA6CB6JB122067", "730800");
vin2priceMap.put("WDCDA6CB6JB122022", "730800");
vin2priceMap.put("WDCDA6CB5JB126496", "730800");
vin2priceMap.put("WDCDA6CB1JB126219", "730800");
vin2priceMap.put("WDCDA6CB5JB125378", "730800");
vin2priceMap.put("WDCDA6CB7JB124667", "730800");
vin2priceMap.put("WDCDA6CB2JB124530", "730800");
vin2priceMap.put("WDCDA6CB9JB124251", "730800");
vin2priceMap.put("WDCDA6CBXJB123898", "730800");
vin2priceMap.put("WDCDA6CB2JB120168", "730800");
vin2priceMap.put("WDCDA6CB3JB121586", "730800");
vin2priceMap.put("WDCDA6CB3JB115321", "730800");
vin2priceMap.put("WDCDA5GB6JB126141", "872500");
vin2priceMap.put("WDCDA5GB0JB122408", "872500");
vin2priceMap.put("WDCDA5GB8JB122611", "872500");
vin2priceMap.put("WDCDA5GB1JB122112", "872500");
vin2priceMap.put("WDCDA5GBXJB126594", "872500");
vin2priceMap.put("WDCDA5GB2JB126928", "872500");
vin2priceMap.put("WDCDA5GB2JB126203", "872500");
vin2priceMap.put("WDCDA5GBXJB125588", "872500");
vin2priceMap.put("WDCDA5GB7JB124222", "872500");
vin2priceMap.put("WDCDA5GB0JB128435", "872500");
vin2priceMap.put("WDCDA5GB5JB128513", "872500");
vin2priceMap.put("WDCDA5GB9JB127672", "872500");
vin2priceMap.put("WDCDA5GB0JB127933", "872500");
vin2priceMap.put("WDCDA5GB6JB128178", "872500");
vin2priceMap.put("WDCDA5GB2JB126282", "872500");
vin2priceMap.put("WDCDA5GB3JB126713", "872500");
vin2priceMap.put("WDCDA5GB5JB127149", "872500");
vin2priceMap.put("WDCDA6CB5JB130709", "730800");
vin2priceMap.put("WDCDA6CBXJB130625", "730800");
vin2priceMap.put("WDCDA6CB9JB129952", "730800");
vin2priceMap.put("WDCDA6CB3JB132751", "730800");
vin2priceMap.put("WDCDA6CB8JB132597", "730800");
vin2priceMap.put("WDCDA6CBXJB133184", "730800");
vin2priceMap.put("WDCDA6CB9JB132768", "730800");
vin2priceMap.put("WDCDA6CB6JB132873", "730800");
vin2priceMap.put("WDCDA6CB8JB132583", "730800");
vin2priceMap.put("WDDBF4DB2JJ745215", "238800");
vin2priceMap.put("WDDBF4DB5JJ747086", "263800");
vin2priceMap.put("WDDBF4DB3JJ747541", "263800");
vin2priceMap.put("WDDBF4DB9JJ753635", "238800");
vin2priceMap.put("WDDBF4DB1JJ753838", "238800");
vin2priceMap.put("WDDBF4DB4JJ753865", "238800");
vin2priceMap.put("WDDBF4DBXJJ754163", "238800");
vin2priceMap.put("WDDBF4DB8JJ762293", "278800");
vin2priceMap.put("WDDBF4DB2JJ762094", "278800");
vin2priceMap.put("WDDBF4DB2JJ762113", "278800");
vin2priceMap.put("WDDBF4DB1JJ762555", "278800");
vin2priceMap.put("WDDBF4DB9JJ762934", "278800");
vin2priceMap.put("WDDBF4DB4JJ762971", "278800");
vin2priceMap.put("WDDBF4DB2JJ762838", "278800");
vin2priceMap.put("WDDBF4DB0JJ762515", "278800");
vin2priceMap.put("WDDBF4DB6JJ762938", "278800");
vin2priceMap.put("WDDBF4DB0JJ763163", "278800");
vin2priceMap.put("WDDSJ4DB2JN644036", "248800");
vin2priceMap.put("WDDSJ4HB8JN647680", "306800");
vin2priceMap.put("WDDSJ4HB3JN646470", "306800");
vin2priceMap.put("WDDMH4DB6JJ479409", "243800");
vin2priceMap.put("WDDMH4DB5JJ479532", "243800");
vin2priceMap.put("WDDMH4DB1JJ479558", "243800");
vin2priceMap.put("WDDMH4DB8JJ480111", "243800");
vin2priceMap.put("WDDMH4DBXJJ479803", "243800");
vin2priceMap.put("WDDMH4DBXJJ479915", "243800");
vin2priceMap.put("WDDMH4DB1JJ480497", "243800");
vin2priceMap.put("WDDMH4DBXJN249249", "243800");
vin2priceMap.put("WDDMH4DB2JN249651", "243800");
vin2priceMap.put("WDDMH4DB5JN249580", "243800");
vin2priceMap.put("WDDMH4DB9JN249632", "262800");
vin2priceMap.put("WDDMH4DB6JN249636", "262800");
vin2priceMap.put("WDDMH4DB1JN249267", "262800");
vin2priceMap.put("WDDMH4DB0JN249552", "262800");
vin2priceMap.put("WDDMH4DB9JJ478755", "262800");
vin2priceMap.put("WDDMH4DB3JN247438", "262800");
vin2priceMap.put("WDDMH4DB8JJ478567", "262800");
vin2priceMap.put("WDDMH4DB0JJ478370", "262800");
vin2priceMap.put("WDDMH4DB7JJ479273", "262800");
vin2priceMap.put("WDDMH4DB5JJ478901", "262800");
vin2priceMap.put("WDDUG6CB7JA407165", "876800");
vin2priceMap.put("WDDUG6CB9JA407037", "876800");
vin2priceMap.put("WDDUG6CB0JA405418", "876800");
vin2priceMap.put("WDDUG6GB9JA398012", "1139800");
vin2priceMap.put("WDDPK3FA0JF152875", "545800");
vin2priceMap.put("WDDPK3FA1JF152805", "545800");
vin2priceMap.put("WDDPK3FA1JF152870", "545800");
vin2priceMap.put("WDDPK3FA2JF152862", "545800");
vin2priceMap.put("WDDPK3FA3JF152949", "545800");
vin2priceMap.put("WDDPK3FA4JF152894", "545800");
vin2priceMap.put("WDDPK3FA5JF152791", "545800");
vin2priceMap.put("WDDPK3FA5JF152855", "545800");
vin2priceMap.put("WDDPK3FA5JF152872", "545800");
vin2priceMap.put("WDDPK3FA6JF152833", "545800");
vin2priceMap.put("WDDPK3FA8JF152848", "545800");
vin2priceMap.put("WDDPK3FA9JF152826", "545800");
vin2priceMap.put("WDDPK3FAXJF152947", "545800");


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



