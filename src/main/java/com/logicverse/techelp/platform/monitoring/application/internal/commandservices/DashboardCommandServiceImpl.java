package com.logicverse.techelp.platform.monitoring.application.internal.commandservices;

import com.logicverse.techelp.platform.monitoring.application.internal.outboundservices.acl.ExternalTechnicalService;
import com.logicverse.techelp.platform.monitoring.domain.model.commands.SubscribeTechnicianToDashBoardCommand;
import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardCommandService;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardCommandServiceImpl implements DashboardCommandService {
    private DashboardRepository dashboardRepository;
    private ExternalTechnicalService externalTechnicalService;

    public DashboardCommandServiceImpl(DashboardRepository dashboardRepository, ExternalTechnicalService externalTechnicalService) {
        this.dashboardRepository = dashboardRepository;
        this.externalTechnicalService = externalTechnicalService;
    }

    @Override
    public Long handle(SubscribeTechnicianToDashBoardCommand command) {
        var technicianId = externalTechnicalService.getTechnicianIdById(command.technicianId().technicianId());
        if (technicianId.isEmpty()) throw new IllegalArgumentException("User "+technicianId+"doesn't exists" );
        dashboardRepository.findByTechnicianId(command.technicianId()).ifPresent(dashBoard -> {
            throw new IllegalArgumentException("Dashboard for "+command.technicianId()+" user already exists");
        });

        //external service to check if client has membership

        var dashboard = new DashBoard(command.technicianId());
        dashboardRepository.save(dashboard);
        return dashboard.getId();
    }
}
