package com.logicverse.techelp.platform.monitoring.interfaces.acl;

import com.logicverse.techelp.platform.monitoring.domain.model.commands.SubscribeTechnicianToDashBoardCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardCommandService;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardQueryService;
import org.springframework.stereotype.Service;

@Service
public class DashBoardContextFacade {
    private final DashboardCommandService dashboardCommandService;
    private final DashboardQueryService dashboardQueryService;
    public DashBoardContextFacade(DashboardCommandService dashboardCommandService, DashboardQueryService dashboardQueryService) {
        this.dashboardCommandService = dashboardCommandService;
        this.dashboardQueryService = dashboardQueryService;
    }

    public Long createDashBoard(Long technicianId){
        var command = new SubscribeTechnicianToDashBoardCommand(new TechnicianId(technicianId));
        var dashBoardId = this.dashboardCommandService.handle(command);
        return dashBoardId;
    }
}
