package com.logicverse.techelp.platform.monitoring.interfaces.rest;

import com.logicverse.techelp.platform.monitoring.domain.model.queries.GetComponentByDashBoardIdQuery;
import com.logicverse.techelp.platform.monitoring.domain.services.ComponentItemCommandService;
import com.logicverse.techelp.platform.monitoring.domain.services.ComponentItemQueryService;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.CreateInventoryResource;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.resources.InventoryResource;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.transform.CreateInventoryFromResourceAssembler;
import com.logicverse.techelp.platform.monitoring.interfaces.rest.transform.InventoryResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/inventory",produces = MediaType.APPLICATION_JSON_VALUE)
public class InventoryController {
    private final ComponentItemQueryService componentItemQueryService;
    private final ComponentItemCommandService componentItemCommandService;

    public InventoryController(ComponentItemQueryService componentItemQueryService, ComponentItemCommandService componentItemCommandService) {
        this.componentItemQueryService = componentItemQueryService;
        this.componentItemCommandService = componentItemCommandService;
    }

    @PostMapping
    public ResponseEntity<InventoryResource> createInventory(@RequestBody CreateInventoryResource resource){
        var commands = CreateInventoryFromResourceAssembler.toCommandFrom(resource);
        commands.stream().forEach(command -> {
            //register components
           componentItemCommandService.handle(command);
        });
        var query = new GetComponentByDashBoardIdQuery(resource.technicalId());
        var componentByQuery = componentItemQueryService.handle(query);

        var inventory = InventoryResourceFromEntityAssembler.toResourceFromEntity(componentByQuery, resource.technicalId());
        return new ResponseEntity<>(inventory, HttpStatus.CREATED);

    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<InventoryResource> getInventoryByDashboardId(@PathVariable Long technicalId) {
        var query = new GetComponentByDashBoardIdQuery(technicalId);
        var components = componentItemQueryService.handle(query);

        var inventory = InventoryResourceFromEntityAssembler.toResourceFromEntity(components,technicalId);
        return ResponseEntity.ok(inventory);
    }
}
