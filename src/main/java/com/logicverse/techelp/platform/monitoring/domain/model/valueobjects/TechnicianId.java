package com.logicverse.techelp.platform.monitoring.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record TechnicianId(Long technicianId) {
    public TechnicianId(){this(0L);}

    public TechnicianId{
        if (technicianId < 0) throw new IllegalArgumentException("Technician Id cannot be negative");
    }
}
