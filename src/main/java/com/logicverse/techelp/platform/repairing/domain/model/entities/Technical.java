package com.logicverse.techelp.platform.repairing.domain.model.entities;

import com.logicverse.techelp.platform.repairing.domain.model.valueobjects.EmailAddress;
import com.logicverse.techelp.platform.repairing.domain.model.valueobjects.PersonName;
import com.logicverse.techelp.platform.shared.domain.model.entities.AuditableModel;
import com.logicverse.techelp.platform.tasking.domain.model.entities.Task;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Technical extends AuditableModel {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PersonName name;
    private String phone;
    @Getter
    private String address;
    @Getter
    private String city;
    @Getter
    private String experience;
    @Getter
    private byte[] photo;
    @Embedded
    private EmailAddress email;
    @Getter
    private String password;
    @Getter
    private String description;
    @Getter
    private String ranking;

    @OneToMany (mappedBy = "technical")
    private List<Task> tasks;

    public Technical(String name, String lastName, String phone, String address, String city,String experience, byte[] photo, String email, String password,
                     String description){
        this.name = new PersonName(name,lastName);
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.photo = photo;
        this.email = new EmailAddress(email);
        this.password = password;
        this.experience = experience;
        this.description = description;
        this.ranking = null;
        this.tasks = new ArrayList<>();
        this.setCreatedAt(new Date());
    }

    public Technical(){}

    public String getFullName(){
        return this.name.getFullName();
    }

    public String getEmailAddress(){
        return this.email.email();
    }
    
}
