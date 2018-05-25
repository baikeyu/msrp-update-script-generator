package com.daimler.dcp.occ

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


final String queryStr = "SELECT {" + DcpProductImportBacklogModel.PK+ "} FROM {" + DcpProductImportBacklogModel._TYPECODE+ "} WHERE {" + DcpProductImportBacklogModel.STATUS + "} = 8796116549723 and {" + DcpProductImportBacklogModel.MODIFIEDTIME+ "} >=?date";
Map<String, Object> param = new HashMap<>();
param.put("date", DateUtils.parseDate("2018-05-25", "yyyy-MM-dd"));
FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr, param);
SearchResult<DcpProductImportBacklogModel> result = flexibleSearchService.search(query);
println(result.getResult().size())
Set<String> vinList = new HashSet<>();

for (backlog in result.getResult()) {
    String vin = backlog.getVin();
    vinList.add(vin);
}
println(vinList)