package com.logicverse.techelp.platform.monitoring.interfaces.rest;

import com.logicverse.techelp.platform.monitoring.domain.model.queries.GetDashboardByTechnicalIdQuery;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardCommandService;
import com.logicverse.techelp.platform.monitoring.domain.services.DashboardQueryService;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.CreateDashboardResource;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.DashboardResource;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.transform.CreateDashboardFromResourceAssembler;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.transform.DashboardResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController {
    private final DashboardQueryService dashboardQueryService;
    private final DashboardCommandService dashboardCommandService;

    public DashboardController(DashboardQueryService dashboardQueryService, DashboardCommandService dashboardCommandService) {
        this.dashboardQueryService = dashboardQueryService;
        this.dashboardCommandService = dashboardCommandService;
    }

    @PostMapping
    public ResponseEntity<DashboardResource> createDashboard(@RequestBody CreateDashboardResource resource){
        var command = CreateDashboardFromResourceAssembler.toCommandFrom(resource);
        var dashboardId = dashboardCommandService.handle(command);

        var query = new GetDashboardByTechnicalIdQuery(new TechnicianId(dashboardId));
        var dashboardByQuery = dashboardQueryService.handle(query);

        var dashBoardResource = DashboardResourceFromEntityAssembler.toResourceFromEntity(dashboardByQuery.get());
        return new ResponseEntity<>(dashBoardResource, HttpStatus.CREATED);
    }

    @GetMapping("/{technicianId}")
    public ResponseEntity<DashboardResource> getDashboardByTechnicianId(@PathVariable Long technicianId){
        var getDashboardByQuery = new GetDashboardByTechnicalIdQuery(new TechnicianId(technicianId));
        var dashboard = dashboardQueryService.handle(getDashboardByQuery);

        var dashboardResource = DashboardResourceFromEntityAssembler.toResourceFromEntity(dashboard.get());
        return ResponseEntity.ok(dashboardResource);
    }
}
