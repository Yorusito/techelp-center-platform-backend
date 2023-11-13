package com.logicverse.techelp.platform.subscription.interfaces.rest.transform;

import com.logicverse.techelp.platform.subscription.interfaces.rest.resources.CreateMembershipResource;

public class CreateMembershipCommandFromResourceAssembler {
    public static CreateMembershipResource toCommandFromResource(CreateMembershipResource resource){
        return new CreateMembershipResource(resource.title(), resource.description(), resource.type(), resource.cost());
    }
}
