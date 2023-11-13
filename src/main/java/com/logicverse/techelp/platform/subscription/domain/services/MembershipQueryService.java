package com.logicverse.techelp.platform.subscription.domain.services;

import com.logicverse.techelp.platform.subscription.domain.model.entities.Membership;
import com.logicverse.techelp.platform.subscription.domain.model.queries.GetAllMembershipsQuery;
import com.logicverse.techelp.platform.subscription.domain.model.queries.GetMembershipByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MembershipQueryService {

    Optional<Membership> handle(GetMembershipByIdQuery query);

    List<Membership> handle(GetAllMembershipsQuery query);
}
