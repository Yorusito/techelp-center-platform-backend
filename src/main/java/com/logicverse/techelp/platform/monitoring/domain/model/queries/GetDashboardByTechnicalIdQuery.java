package com.logicverse.techelp.platform.monitoring.domain.model.queries;

import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;

public record GetDashboardByTechnicalIdQuery(TechnicianId technicianId) {
}
