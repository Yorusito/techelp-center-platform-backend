package com.logicverse.techelp.platform.monitoring.application.internal.outboundservices.acl;

import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.repairing.interfaces.acl.TechnicalContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalTechnicalService {
    private final TechnicalContextFacade technicalContextFacade;

    public ExternalTechnicalService(TechnicalContextFacade technicalContextFacade) {
        this.technicalContextFacade = technicalContextFacade;
    }

    public Optional<TechnicianId> getTechnicianIdById(Long technicalId){
        var technicianId = technicalContextFacade.getTechnicalById(technicalId);
        if (technicianId == 0L) return Optional.empty();
        return Optional.of(new TechnicianId(technicianId));
    }
}
