package com.logicverse.techelp.platform.monitoring.infrastructure.persistence.jpa.repositories;

import com.logicverse.techelp.platform.monitoring.domain.model.entities.DashBoard;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DashboardRepository extends JpaRepository<DashBoard,Long> {
    Optional<DashBoard> findByTechnicianId(TechnicianId technicianId);
}
