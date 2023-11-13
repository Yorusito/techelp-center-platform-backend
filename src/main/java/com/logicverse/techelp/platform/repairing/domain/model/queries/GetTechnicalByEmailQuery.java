package com.logicverse.techelp.platform.repairing.domain.model.queries;

import com.logicverse.techelp.platform.repairing.domain.model.valueobjects.EmailAddress;

public record GetTechnicalByEmailQuery(EmailAddress email) {
}
