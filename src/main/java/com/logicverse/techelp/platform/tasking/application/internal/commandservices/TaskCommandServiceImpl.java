package com.logicverse.techelp.platform.tasking.application.internal.commandservices;

import com.logicverse.techelp.platform.monitoring.domain.model.valueobjects.TechnicianId;
import com.logicverse.techelp.platform.repairing.infrastructure.persistence.jpa.repositories.TechnicalRepository;
import com.logicverse.techelp.platform.tasking.domain.model.commands.CreateTaskCommand;
import com.logicverse.techelp.platform.tasking.domain.model.entities.Task;
import com.logicverse.techelp.platform.tasking.domain.model.queries.GetTaskByIdQuery;
import com.logicverse.techelp.platform.tasking.domain.services.TaskCommandService;
import com.logicverse.techelp.platform.tasking.infrestructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {
    private TaskRepository taskRepository;
    private TechnicalRepository technicalRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository, TechnicalRepository technicalRepository){
        this.taskRepository=taskRepository;
        this.technicalRepository = technicalRepository;
    }

    @Override
    public  Long handle(CreateTaskCommand command){
        var technical = technicalRepository.findById(command.technicalId());
        if (technical.isEmpty()) throw new IllegalArgumentException("Provided technical does not exist");
        var newTask = new Task(command.client_name(),command.client_phone(),command.problem(),
                command.component(),command.delivery_date(),command.income(), technical.get());
        this.taskRepository.save(newTask);
        return newTask.getId();
    }
}
