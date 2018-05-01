import com.daimler.dcp.biz.china.enums.OwnershipStatusEnum
import com.daimler.dcp.biz.china.model.DcpOwnershipModel
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import com.daimler.dcp.biz.china.model.DcpProductImportBacklogModel
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import org.apache.commons.lang3.time.DateFormatUtils
import org.apache.commons.lang3.time.DateUtils;

flexibleSearchService = spring.getBean "flexibleSearchService"
dcpOwnershipService = spring.getBean "dcpOwnershipService"

final PointOfServiceModel posEx = new PointOfServiceModel()
posEx.setActive(true);
Map<String, String> offliceVins = new HashMap<>();
//String posName = "NBJL60";
//posEx.setName(posName);
//final PointOfServiceModel pos = null;
//try {
//	pos = flexibleSearchService.getModelByExample(posEx);
//} catch(ModelNotFoundException e) {
//	println "Can't find pos "+ posName;
//	return
//}

List<PointOfServiceModel> poses = flexibleSearchService.getModelsByExample(posEx);

for (pos in poses) {
    List<String> searchList = new ArrayList<>();
    final DcpOwnershipModel ownershipEx = new DcpOwnershipModel();
    ownershipEx.setPointOfService(pos);
    ownershipEx.setStatus(OwnershipStatusEnum.INSTOCK);

    List<DcpOwnershipModel> stockList = flexibleSearchService.getModelsByExample(ownershipEx);

    for(stock in stockList)
    {
        searchList.add(stock.getVin());
    }

    for (vin in searchList) {
        final DcpProductImportBacklogModel backlogEx = new DcpProductImportBacklogModel()
        backlogEx.setVin(vin);
        final DcpProductImportBacklogModel backlog = null;
        try {
            backlog = flexibleSearchService.getModelByExample(backlogEx);
        } catch(ModelNotFoundException e) {
            println "Can't find vin"+ vin;
            continue
        }

        Date modifiedDate = backlog.getModifiedtime();

        if (modifiedDate.before(DateUtils.parseDate("2018-05-01", "yyyy-MM-dd"))) {
            offliceVins.put(vin, pos.getName());
        }
    }
}

println(offliceVins.size());

for (key in offliceVins.entrySet()) {
    println(String.format("vinMap.put(\"%s\", \"%s\");", key.getKey(), key.getValue()));
}




