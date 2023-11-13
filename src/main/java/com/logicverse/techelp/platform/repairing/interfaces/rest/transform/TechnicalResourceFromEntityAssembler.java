package com.logicverse.techelp.platform.repairing.interfaces.rest.transform;

import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.TechnicalResource;

public class TechnicalResourceFromEntityAssembler {
    public static TechnicalResource toResourceFromEntity(Technical entity){
        return new TechnicalResource(entity.getId(),entity.getFullName(),entity.getAddress(),entity.getCity(),
                entity.getExperience(),entity.getPhoto(),entity.getDescription(),entity.getRanking());
    }
}
