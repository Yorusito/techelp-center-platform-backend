package com.logicverse.techelp.platform.monitoring.application.internal.commandservices;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.CreateComponentItemCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.entities.ComponentItem;
import com.logicverse.techelp.platform.monitoring.domain.services.ComponentItemCommandService;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.ComponentItemRepository;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponentItemCommandServiceImpl implements ComponentItemCommandService {
    private ComponentItemRepository componentItemRepository;
    private DashboardRepository dashboardRepository;

    public ComponentItemCommandServiceImpl(ComponentItemRepository componentItemRepository, DashboardRepository dashboardRepository) {
        this.componentItemRepository = componentItemRepository;
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Long handle(CreateComponentItemCommand command) {
        var dashboard = dashboardRepository.findById(command.dashBoardId());
        if (dashboard.isEmpty()) throw new IllegalArgumentException("Buy our membership to access inventory");
        var component = new ComponentItem(command.name(), command.quantity(),command.price(),dashboard.get());
        componentItemRepository.save(component);
        return component.getId();
    }
}
