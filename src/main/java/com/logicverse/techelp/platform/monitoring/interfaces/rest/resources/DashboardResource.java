package com.logicverse.techelp.platform.monitoring.interfaces.rest.resources;

import java.util.List;

public record DashboardResource(
        Long id,
        List<RevenueResource> revenues
) {
}
