package com.logicverse.techelp.platform.repairing.application.internal.commandservices;

import com.logicverse.techelp.platform.repairing.domain.model.commands.CreateTechnicalCommand;
import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.repairing.domain.model.valueobjects.EmailAddress;
import com.logicverse.techelp.platform.repairing.domain.services.TechnicalCommandService;
import com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories.TechnicalRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnicalCommandServiceImpl implements TechnicalCommandService {
    private TechnicalRepository technicalRepository;

    public TechnicalCommandServiceImpl(TechnicalRepository technicalRepository){
        this.technicalRepository = technicalRepository;
    }
    @Override
    public Long handle(CreateTechnicalCommand command) {
        var email = new EmailAddress(command.email());
        var technical = this.technicalRepository.findByEmail(email);
        if (technical.isEmpty()) {
            var newTechnical = new Technical(command.name(),command.lastName(),
                    command.phone(),command.address(),command.city(),
                    command.experience(), command.photo(), command.email(),
                    command.password(),command.description());
            this.technicalRepository.save(newTechnical);
            return newTechnical.getId();
        }
        throw new IllegalArgumentException("Profile with email "+command.email()+" already exists");
    }
}
