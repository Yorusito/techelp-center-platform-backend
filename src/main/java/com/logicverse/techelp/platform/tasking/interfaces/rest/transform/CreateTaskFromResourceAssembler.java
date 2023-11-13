package com.logicverse.techelp.platform.tasking.interfaces.rest.transform;

import com.logicverse.techelp.platform.tasking.domain.model.commands.CreateTaskCommand;
import com.logicverse.techelp.platform.tasking.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskFromResourceAssembler {
    public static CreateTaskCommand toCommandFrom(CreateTaskResource resource){
        return new CreateTaskCommand(resource.client_name(), resource.client_phone(), resource.problem(), resource.component(),resource.delivery_date(),resource.income(),resource.technicalId());
    }
}
