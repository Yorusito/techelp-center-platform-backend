package com.logicverse.techelp.platform.repairing.domain.services;

import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByEmailQuery;
import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TechnicalQueryService {
    Optional<Technical> handle(GetTechnicalByIdQuery query);
    Optional<Technical> handle(GetTechnicalByEmailQuery query);
    List<Technical> handle();
}
