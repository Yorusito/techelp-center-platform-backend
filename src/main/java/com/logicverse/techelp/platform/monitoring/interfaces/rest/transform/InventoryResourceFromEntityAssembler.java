package com.logicverse.techelp.platform.monitoring.interfaces.rest.transform;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.ComponentItem;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.ComponentResource;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.InventoryResource;

import java.util.List;

public class InventoryResourceFromEntityAssembler {
    public static InventoryResource toResourceFromEntity(List<ComponentItem> items, Long technicalId){
        var itemsResource = items.stream().map(componentItem -> {
            return new ComponentResource(componentItem.getId(),componentItem.getName(),
                    componentItem.getQuantity(),componentItem.getPrice());
        });
        return new InventoryResource(technicalId,itemsResource.toList());
    }
}
