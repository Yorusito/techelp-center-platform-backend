package com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories;

import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.repairing.domain.model.valueobjects.EmailAddress;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TechnicalRepository extends JpaRepository<Technical, Long> {
    Optional<Technical> findByEmail(EmailAddress email);
}
