package com.logicverse.techelp.platform.tasking.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ClientProblem(String problem) {
    public ClientProblem(){this(null);}

    public ClientProblem {
        if (problem == null || problem.isBlank()) {
            throw new IllegalArgumentException("The problem cannot be null or blank");
        }
    }

    public String getClientProblem() {
        return String.format("%s", problem);
    }
}
