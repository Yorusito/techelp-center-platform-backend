package com.logicverse.techelp.platform.tasking.domain.model.entities;
import com.logicverse.techelp.platform.repairing.domain.model.entities.Technical;
import com.logicverse.techelp.platform.shared.domain.model.entities.AuditableModel;
import com.logicverse.techelp.platform.tasking.domain.model.valueobjects.ClientName;
import com.logicverse.techelp.platform.tasking.domain.model.valueobjects.ClientPhoneName;
import com.logicverse.techelp.platform.tasking.domain.model.valueobjects.ClientProblem;
import com.logicverse.techelp.platform.tasking.domain.model.valueobjects.DeliveryDay;
import jakarta.persistence.*;
import lombok.Getter;


import java.util.Date;


@Entity
public class Task extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Getter
    private ClientName name;

    @Embedded
    @Getter
    private ClientPhoneName clientphone;

    @Embedded
    @Getter
    private ClientProblem problem;

    @Getter
    private String componentItem;

    @Embedded
    @Getter
    private DeliveryDay deliveryDay;

    @Getter
    private String income;

    @Getter
    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;

    public Task(String client_name, String client_phone, String problem, String component, Date delivery_date, String income, Technical technical){
        this.componentItem =component;
        this.name = new ClientName(client_name);
        this.clientphone = new ClientPhoneName(client_phone);
        this.deliveryDay = new DeliveryDay(delivery_date);
        this.problem = new ClientProblem(problem);
        this.income = income;
        this.technical = technical;
    }

    public Task(){}

    public String getClientName(){
        return this.name.getClientName();
    }

    public String getClientPhoneName(){
        return this.clientphone.getClientPhoneName();
    }

    public String getClientProblem(){
        return this.problem.getClientProblem();
    }

    public Date getDeliveryDate(){ return  this.deliveryDay.getDeliveryDay();}


}
