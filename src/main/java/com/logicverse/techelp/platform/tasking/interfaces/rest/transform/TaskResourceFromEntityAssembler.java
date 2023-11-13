package com.logicverse.techelp.platform.tasking.interfaces.rest.transform;

import com.logicverse.techelp.platform.tasking.domain.model.entities.Task;
import com.logicverse.techelp.platform.tasking.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {

    public static TaskResource toResourceFromEntity (Task entity){
        return new TaskResource(entity.getClientName(), entity.getClientPhoneName(),
                entity.getClientProblem(),entity.getComponentItem(),entity.getDeliveryDate(), entity.getIncome(), entity.getTechnical().getId());
    }
}
