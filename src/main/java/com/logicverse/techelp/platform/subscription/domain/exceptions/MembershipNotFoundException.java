package com.logicverse.techelp.platform.subscription.domain.exceptions;

public class MembershipNotFoundException extends RuntimeException{
    public MembershipNotFoundException(Long membershipId) {
        super("Membership with Id" + membershipId + "not found");
    }
}
