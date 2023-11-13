package com.logicverse.techelp.platform.monitoring.application.internal.queryservices;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.domain.model.queries.GetDashboardByTechnicalIdQuery;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardQueryService;
import com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class DashboardQueryServiceImpl implements DashboardQueryService {
    private DashboardRepository dashboardRepository;

    public DashboardQueryServiceImpl(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public Optional<DashBoard> handle(GetDashboardByTechnicalIdQuery query) {
        return dashboardRepository.findByTechnicianId(query.technicianId());
    }
}
