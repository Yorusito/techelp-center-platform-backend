package com.logicverse.techelp.platform.subscription.interfaces.rest.transform;

import com.logicverse.techelp.platform.subscription.domain.model.commands.UpdateMembershipCommand;
import com.logicverse.techelp.platform.subscription.interfaces.rest.resources.UpdateMembershipResource;

public class UpdateMembershipCommandFromResourceAssembler {

    public static UpdateMembershipCommand toCommandFromResource(Long membershipId, UpdateMembershipResource resource){
        return new UpdateMembershipCommand(membershipId, resource.title(), resource.description(),resource.type(),resource.cost());
    }
}
