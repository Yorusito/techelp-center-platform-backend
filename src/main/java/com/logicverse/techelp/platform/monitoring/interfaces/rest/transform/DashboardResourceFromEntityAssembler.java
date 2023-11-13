package com.logicverse.techelp.platform.monitoring.interfaces.rest.transform;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.DashboardResource;

public class DashboardResourceFromEntityAssembler {
    public static DashboardResource toResourceFromEntity(DashBoard entity){
        return new DashboardResource(entity.getId(),null);
    }
}
