package com.logicverse.techelp.platform.repairing.interfaces.acl;

import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByIdQuery;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalCommandService;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalQueryService;
import org.springframework.stereotype.Service;

@Service
public class TechnicalContextFacade {
    private final TechnicalCommandService technicalCommandService;
    private final TechnicalQueryService technicalQueryService;

    public TechnicalContextFacade(TechnicalCommandService technicalCommandService, TechnicalQueryService technicalQueryService) {
        this.technicalCommandService = technicalCommandService;
        this.technicalQueryService = technicalQueryService;
    }

    public Long getTechnicalById(Long technicalId){
        var command = new GetTechnicalByIdQuery(technicalId);
        var technical = this.technicalQueryService.handle(command);
        if (technical.isEmpty()) return 0L;
        return technical.get().getId();
    }
}
