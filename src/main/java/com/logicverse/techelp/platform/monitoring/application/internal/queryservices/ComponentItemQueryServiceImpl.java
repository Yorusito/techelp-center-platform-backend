package com.logicverse.techelp.platform.monitoring.application.internal.queryservices;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.ComponentItem;
import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.domain.model.queries.GetComponentByDashBoardIdQuery;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.monitoring.domain.services.ComponentItemQueryService;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.ComponentItemRepository;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ComponentItemQueryServiceImpl implements ComponentItemQueryService {
    private ComponentItemRepository componentItemRepository;
    private DashboardRepository dashboardRepository;

    public ComponentItemQueryServiceImpl(ComponentItemRepository componentItemRepository, DashboardRepository dashboardRepository) {
        this.componentItemRepository = componentItemRepository;
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public List<ComponentItem> handle(GetComponentByDashBoardIdQuery query) {
        var dashBoard = dashboardRepository.findByTechnicianId(new TechnicianId(query.dashBoardId()));
        return componentItemRepository.findComponentItemByDashBoard(dashBoard.get());
    }
}
