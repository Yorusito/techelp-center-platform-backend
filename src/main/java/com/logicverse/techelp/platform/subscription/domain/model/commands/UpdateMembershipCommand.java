package com.logicverse.techelp.platform.subscription.domain.model.commands;

public record UpdateMembershipCommand(Long id, String title, String description, Integer type, Long cost) {
}
