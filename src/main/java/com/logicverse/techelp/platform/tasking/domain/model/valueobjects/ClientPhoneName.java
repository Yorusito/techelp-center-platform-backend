package com.logicverse.techelp.platform.tasking.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public record ClientPhoneName(String client_phone) {
    public ClientPhoneName(){this(null);}

    public ClientPhoneName {
        if (client_phone == null || client_phone.isBlank()) {
            throw new IllegalArgumentException("Client phone cannot be null or blank");
        }
    }

    public String getClientPhoneName() {
        return String.format("%s", client_phone);
    }
}
