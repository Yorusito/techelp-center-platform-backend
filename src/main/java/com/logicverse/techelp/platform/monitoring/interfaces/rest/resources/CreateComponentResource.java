package com.logicverse.techelp.platform.monitoring.interfaces.rest.resources;

public record CreateComponentResource(
        String name,
        Long quantity,
        double price
) {
}
