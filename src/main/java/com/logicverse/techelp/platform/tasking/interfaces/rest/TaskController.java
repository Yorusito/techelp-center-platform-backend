package com.logicverse.techelp.platform.tasking.interfaces.rest;

import com.logicverse.techelp.platform.tasking.domain.model.queries.GetTaskByIdQuery;
import com.logicverse.techelp.platform.tasking.domain.model.queries.GetTaskByTechnicalIdQuery;
import com.logicverse.techelp.platform.tasking.domain.services.TaskCommandService;
import com.logicverse.techelp.platform.tasking.domain.services.TaskQueryService;
import com.logicverse.techelp.platform.tasking.interfaces.rest.resources.CreateTaskResource;
import com.logicverse.techelp.platform.tasking.interfaces.rest.resources.TaskResource;
import com.logicverse.techelp.platform.tasking.interfaces.rest.transform.CreateTaskFromResourceAssembler;
import com.logicverse.techelp.platform.tasking.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(value="api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskQueryService taskQueryService;
    private final TaskCommandService taskCommandService;

    public TaskController(TaskQueryService taskQueryService, TaskCommandService taskCommandService) {
        this.taskQueryService = taskQueryService;
        this.taskCommandService = taskCommandService;
    }


    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource) {
        var command = CreateTaskFromResourceAssembler.toCommandFrom(resource);
        var taskId = taskCommandService.handle(command);

        var query = new GetTaskByIdQuery(taskId);
        var taskByQuery = taskQueryService.handle(query);

        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(taskByQuery.get());
        return new ResponseEntity<>(taskResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Stream<TaskResource>> getTasks() {
        var tasks = taskQueryService.handle();
        if (tasks.isEmpty()) return ResponseEntity.badRequest().build();
        var tasksResource = tasks.stream().map(task -> {
            return TaskResourceFromEntityAssembler.toResourceFromEntity(task);
        });
        return ResponseEntity.ok(tasksResource);
    }

    @GetMapping("/{technicalId}")
    public ResponseEntity<Stream<TaskResource>> getTasksByTechnicalId(@PathVariable Long technicalId) {
        var tasks = taskQueryService.handle(new GetTaskByTechnicalIdQuery(technicalId));
        if (tasks.isEmpty()) return ResponseEntity.badRequest().build();
        var tasksResource = tasks.stream().map(task -> {
            return TaskResourceFromEntityAssembler.toResourceFromEntity(task);
        });
        return ResponseEntity.ok(tasksResource);
    }
}
