import com.daimler.dcp.biz.china.model.DcpOwnershipModel
import de.hybris.platform.storelocator.model.PointOfServiceModel
import com.daimler.dcp.biz.china.enums.OwnershipStatusEnum;
import java.util.Collection
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
dcpVehicleNstDao = spring.getBean "dcpVehicleNstDao"
modelService = spring.getBean "modelService"
currencyDao = spring.getBean "currencyDao"

final PointOfServiceModel posEx = new PointOfServiceModel()
posEx.setName("CentralPOS");
final PointOfServiceModel centerPos = null;
try {
    centerPos = flexibleSearchService.getModelByExample(posEx);
} catch(ModelNotFoundException e) {
    println "Can't find pos"+ "CentralPOS";
    return
}

Collection<DcpOwnershipModel> ownershipList = centerPos.getOwnership();

for(vin in ownershipList)
{
    if(OwnershipStatusEnum.INSTOCK.equals(vin.getStatus())) {
        try {
            dcpOwnershipService.unclaim(vin.getVin(), false);
        }
        catch (Exception exc)
        {
            println "Unclaim failed, vin -> "+ vin.getVin();
            continue
        }
        println "unclaim success, vin -> "+ vin.getVin();
    }
}
