package com.logicverse.techelp.platform.repairing.interfaces.rest.transform;

import com.logicverse.techelp.platform.repairing.domain.model.commands.CreateTechnicalCommand;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.CreateTechnicalResource;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.TechnicalResource;

import java.util.Base64;

public class CreateTechnicalFromResourceAssembler {
    public static CreateTechnicalCommand toCommandFrom(CreateTechnicalResource resource){
        return new CreateTechnicalCommand(resource.name(),resource.lastName(),resource.phone(),resource.address(),
                resource.city(),resource.experience(), Base64.getDecoder().decode(resource.photo()),resource.email(),resource.password(),resource.description());
    }
}
