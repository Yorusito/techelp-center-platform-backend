package com.logicverse.techelp.platform.monitoring.interfaces.rest.transform;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.SubscribeTechnicianToDashBoardCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.CreateDashboardResource;

public class CreateDashboardFromResourceAssembler {
    public static SubscribeTechnicianToDashBoardCommand toCommandFrom(CreateDashboardResource resource){
        return new SubscribeTechnicianToDashBoardCommand(new TechnicianId(resource.technicalId()));
    }
}
