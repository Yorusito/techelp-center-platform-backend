package com.logicverse.techelp.platform.repairing.interfaces.rest.resources;

public record CreateTechnicalResource(

        String name,
        String lastName,
        String phone,
        String address,
        String city,
        String experience,
        String photo,
        String email,
        String password,
        String description
) {
}
