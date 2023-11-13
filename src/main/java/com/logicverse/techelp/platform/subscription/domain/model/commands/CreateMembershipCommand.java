package com.logicverse.techelp.platform.subscription.domain.model.commands;

public record CreateMembershipCommand(String title, String description, Integer type, Long cost) {
}
