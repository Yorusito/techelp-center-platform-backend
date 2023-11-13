package com.logicverse.techelp.platform.repairing.interfaces.rest.resources;

public record TechnicalResource(
        Long id,
        String fullName,
        String address,
        String city,
        String experience,
        byte[] photo,
        String description,
        String ranking
) {
}
