package com.logicverse.techelp.platform.monitoring.domain.services;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.ComponentItem;
import com.logicverse.techelp.platform.monitoring.domain.model.queries.GetComponentByDashBoardIdQuery;

import java.util.List;
import java.util.Optional;

public interface ComponentItemQueryService {
    List<ComponentItem> handle(GetComponentByDashBoardIdQuery query);
}
