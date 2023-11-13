package com.logicverse.techelp.platform.tasking.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public record ClientName(String client_name) {
    public ClientName(){this(null);}

    public ClientName {
        if (client_name == null || client_name.isBlank()) {
            throw new IllegalArgumentException("Client name cannot be null or blank");
        }
    }

    public String getClientName() {
        return String.format("%s", client_name);
    }
}
