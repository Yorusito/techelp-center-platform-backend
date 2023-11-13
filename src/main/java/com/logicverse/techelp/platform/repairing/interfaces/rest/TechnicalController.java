package com.logicverse.techelp.platform.repairing.interfaces.rest;

import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByIdQuery;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalCommandService;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalQueryService;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.CreateTechnicalResource;
import com.logicverse.techelp.platform.repairing.interfaces.rest.resources.TechnicalResource;
import com.logicverse.techelp.platform.repairing.interfaces.rest.transform.CreateTechnicalFromResourceAssembler;
import com.logicverse.techelp.platform.repairing.interfaces.rest.transform.TechnicalResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(value="api/v1/technicals", produces = MediaType.APPLICATION_JSON_VALUE)
public class TechnicalController {
    private final TechnicalQueryService technicalQueryService;
    private final TechnicalCommandService technicalCommandService;

    public TechnicalController(TechnicalQueryService technicalQueryService, TechnicalCommandService technicalCommandService) {
        this.technicalQueryService = technicalQueryService;
        this.technicalCommandService = technicalCommandService;
    }


    @PostMapping
    public ResponseEntity<TechnicalResource> createTechnical(@RequestBody CreateTechnicalResource resource) {
        var command = CreateTechnicalFromResourceAssembler.toCommandFrom(resource);
        var technicalId = technicalCommandService.handle(command);

        var query = new GetTechnicalByIdQuery(technicalId);
        var technicalByQuery = technicalQueryService.handle(query);

        var technicalResource = TechnicalResourceFromEntityAssembler.toResourceFromEntity(technicalByQuery.get());
        return new ResponseEntity<>(technicalResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Stream<TechnicalResource>> getTechnicals() {
        var technicals = technicalQueryService.handle();
        if (technicals.isEmpty()) return ResponseEntity.badRequest().build();
        var technicalsResource = technicals.stream().map(technical -> {
            return TechnicalResourceFromEntityAssembler.toResourceFromEntity(technical);
        });
        return ResponseEntity.ok(technicalsResource);
    }
}
