package com.logicverse.techelp.platform.subscription.application.internal.commandservices;

import com.logicverse.techelp.platform.subscription.domain.model.commands.CreateMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.commands.DeleteMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.commands.UpdateMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.entities.Membership;
import com.logicverse.techelp.platform.subscription.domain.services.MembershipCommandService;
import com.logicverse.techelp.platform.subscription.infrastructure.persistence.jpa.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipCommandServiceImpl implements MembershipCommandService {

    private final MembershipRepository membershipRepository;

    public MembershipCommandServiceImpl(MembershipRepository membershipRepository) {this.membershipRepository = membershipRepository;}

    @Override
    public Long handle(CreateMembershipCommand command){
        if (membershipRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("Membership with same title already exists");
        }
        var membership = new Membership(command.title(), command.description(), command.type(), command.cost());
        membershipRepository.save(membership);
        return membership.getId();
    }

    @Override
    public Optional<Membership> handle(UpdateMembershipCommand command){
        if(!membershipRepository.existsById(command.id())) throw new IllegalArgumentException("Membership does not exist");
        var membershipToUpdate = membershipRepository.findById(command.id()).get();
        if(membershipRepository.existsByTitleAndIdIsNot(command.title(), command.id()))
            throw new IllegalArgumentException("Membership with same title already exists");
        var updatedMembership = membershipRepository.save(membershipToUpdate.updateInformation(command.title(), command.description(), command.type(), command.cost()));
        return Optional.of(updatedMembership);
    }

    @Override
    public void handle(DeleteMembershipCommand command){
        if(!membershipRepository.existsById(command.membershipId())){
            throw new IllegalArgumentException("Membership does not exist");
        }
        membershipRepository.deleteById(command.membershipId());
    }

}
