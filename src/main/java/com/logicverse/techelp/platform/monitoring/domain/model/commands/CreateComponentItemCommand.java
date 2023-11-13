package com.logicverse.techelp.platform.monitoring.domain.model.commands;


public record CreateComponentItemCommand(String name, Long quantity, double price, Long dashBoardId) {
}
