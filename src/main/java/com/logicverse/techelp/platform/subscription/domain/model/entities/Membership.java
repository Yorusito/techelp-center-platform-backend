package com.logicverse.techelp.platform.subscription.domain.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Entity

public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer type;

    private Long cost;

    public Membership() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
    }
    public Membership(String title, String description, Integer type, Long cost) {
        this();
        this.title = title;
        this.description = description;
        this.type = type;
        this.cost = cost;
    }


    public Membership updateInformation(String title, String description, Integer type, Long cost) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.cost = cost;
        return this;
    }

}
