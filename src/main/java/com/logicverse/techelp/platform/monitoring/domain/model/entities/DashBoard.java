package com.logicverse.techelp.platform.monitoring.domain.model.entities;

import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.Inventory;
import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
public class DashBoard extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @Getter
    private TechnicianId technicianId;

    @Embedded
    private Inventory inventory;
    @OneToMany
    private List<Revenue> incomes;

    public DashBoard(){}

    public DashBoard(Long technicianId){
        this.technicianId = new TechnicianId(technicianId);
    }

    public DashBoard(TechnicianId technicianId){
        this.technicianId = technicianId;
    }

    public void updateInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public void addIncomePerWeekend(Long income){
        this.incomes.add(new Revenue(income));
    }
}
