import de.hybris.platform.customerreview.model.CustomerReviewModel
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel;
import com.daimler.dcp.biz.china.enums.DcpProductImportStatus
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.servicelayer.search.SearchResult
import org.apache.commons.lang3.time.DateUtils;
flexibleSearchService = spring.getBean "flexibleSearchService"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

HashMap<String, String> vin2priceMap = new HashMap();
//put all start price here
vin2priceMap.put("YYYANNCNSS4000145", "163000");
vin2priceMap.put("LE4ZG4JB7HL095896", "474700");

final String queryStr = "SELECT {" + DcpProductImportBacklogModel.PK+ "} FROM {" + DcpProductImportBacklogModel._TYPECODE+ "} WHERE {" + DcpProductImportBacklogModel.COMMENT + "} LIKE ?msg and {" + DcpProductImportBacklogModel.MODIFIEDTIME+ "} >=?date";
Map<String, Object> param = new HashMap<>();
param.put("msg", "价格不能低于%");
param.put("date", DateUtils.parseDate("2018-05-01", "yyyy-MM-dd"));
FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr, param);
SearchResult<DcpProductImportBacklogModel> result = flexibleSearchService.search(query);
println(result.getResult().size())

for (backlog in result.getResult()) {
    println(backlog.getVin() + backlog.getDmsFileName() + backlog.getModifiedtime())
    backlog.setStatus(DcpProductImportStatus.REIMPORT);
    modelService.save(backlog);
}