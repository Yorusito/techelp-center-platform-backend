package com.logicverse.techelp.platform.monitoring.domain.services;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.CreateComponentItemCommand;

public interface ComponentItemCommandService {
    Long handle(CreateComponentItemCommand command);
}
