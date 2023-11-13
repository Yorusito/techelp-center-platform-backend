package com.logicverse.techelp.platform.repairing.application.internal.queryservices;

import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByEmailQuery;
import com.logicverse.techelp.platform.repairing.domain.model.queries.GetTechnicalByIdQuery;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalQueryService;
import com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories.TechnicalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicalQueryServiceImpl implements TechnicalQueryService {
    private TechnicalRepository technicalRepository;

    public TechnicalQueryServiceImpl(TechnicalRepository technicalRepository) {
        this.technicalRepository = technicalRepository;
    }

    @Override
    public Optional<Technical> handle(GetTechnicalByIdQuery query) {
        return  technicalRepository.findById(query.technicalId());
    }

    @Override
    public Optional<Technical> handle(GetTechnicalByEmailQuery query) {
        return technicalRepository.findByEmail(query.email());
    }

    @Override
    public List<Technical> handle() {
        return technicalRepository.findAll();
    }
}
