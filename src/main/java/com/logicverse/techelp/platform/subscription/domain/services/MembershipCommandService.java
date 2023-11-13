package com.logicverse.techelp.platform.subscription.domain.services;

import com.logicverse.techelp.platform.subscription.domain.model.commands.CreateMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.commands.DeleteMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.commands.UpdateMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.entities.Membership;

import java.util.Optional;

public interface MembershipCommandService {

    Long handle(CreateMembershipCommand command);

    Optional<Membership> handle(UpdateMembershipCommand command);

    void handle(DeleteMembershipCommand command);

}
