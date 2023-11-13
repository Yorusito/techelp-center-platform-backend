package com.logicverse.techelp.platform.monitoring.interfaces.rest.resources;

import java.util.List;

public record InventoryResource(
        Long technicalId,
        List<ComponentResource> items
) {
}
