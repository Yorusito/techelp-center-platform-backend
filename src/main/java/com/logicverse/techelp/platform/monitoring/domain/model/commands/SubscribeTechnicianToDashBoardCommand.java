package com.logicverse.techelp.platform.monitoring.domain.model.commands;

import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;

public record SubscribeTechnicianToDashBoardCommand(TechnicianId technicianId) {
}
