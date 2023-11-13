package com.logicverse.techelp.platform.monitoring.domain.services;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.SubscribeTechnicianToDashBoardCommand;

public interface DashboardCommandService {
    Long handle(SubscribeTechnicianToDashBoardCommand command);
}
