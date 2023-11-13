package com.logicverse.techelp.platform.repairing.domain.services;

import com.logicverse.techelp.platform.repairing.domain.model.commands.CreateTechnicalCommand;


public interface TechnicalCommandService {
    Long handle(CreateTechnicalCommand command);
}
