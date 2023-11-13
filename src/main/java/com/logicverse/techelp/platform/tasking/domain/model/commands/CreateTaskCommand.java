package com.logicverse.techelp.platform.tasking.domain.model.commands;


import java.util.Date;

public record CreateTaskCommand(String client_name, String client_phone, String problem, String component, Date delivery_date, String income, Long technicalId) {

}
