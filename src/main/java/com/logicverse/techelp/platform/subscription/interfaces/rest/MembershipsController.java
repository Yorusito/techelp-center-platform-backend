package com.logicverse.techelp.platform.subscription.interfaces.rest;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


import com.logicverse.techelp.platform.subscription.domain.model.commands.CreateMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.commands.DeleteMembershipCommand;
import com.logicverse.techelp.platform.subscription.domain.model.queries.GetAllMembershipsQuery;
import com.logicverse.techelp.platform.subscription.domain.model.queries.GetMembershipByIdQuery;
import com.logicverse.techelp.platform.subscription.domain.services.MembershipCommandService;
import com.logicverse.techelp.platform.subscription.domain.services.MembershipQueryService;
import com.logicverse.techelp.platform.subscription.interfaces.rest.resources.CreateMembershipResource;
import com.logicverse.techelp.platform.subscription.interfaces.rest.resources.MembershipResource;
import com.logicverse.techelp.platform.subscription.interfaces.rest.resources.UpdateMembershipResource;
import com.logicverse.techelp.platform.subscription.interfaces.rest.transform.MembershipResourceFromEntityAssembler;
import com.logicverse.techelp.platform.subscription.interfaces.rest.transform.UpdateMembershipCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/membership", produces = APPLICATION_JSON_VALUE)
public class MembershipsController {

    private final MembershipCommandService membershipCommandService;

    private final MembershipQueryService membershipQueryService;

    public MembershipsController(MembershipCommandService membershipCommandService, MembershipQueryService membershipQueryService) {
        this.membershipCommandService = membershipCommandService;
        this.membershipQueryService = membershipQueryService;
    }


    @PostMapping
    public ResponseEntity<MembershipResource> createMembership(@RequestBody CreateMembershipResource createMembershipResource){
        var CreateMembershipCommand = new CreateMembershipCommand(createMembershipResource.title(), createMembershipResource.description(), createMembershipResource.type(),createMembershipResource.cost());
        var membershipId = membershipCommandService.handle(CreateMembershipCommand);
        if (membershipId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getMembershipByIdQuery = new GetMembershipByIdQuery(membershipId);
        var membership = membershipQueryService.handle(getMembershipByIdQuery);
        if (membership.isEmpty()) return ResponseEntity.badRequest().build();
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(membership.get());
        return new ResponseEntity<>(membershipResource, HttpStatus.CREATED);
    }

    @GetMapping("/{membershipId}")
    public ResponseEntity<MembershipResource> getMembershipById(@PathVariable Long membershipId){
        var getMembershipByIdQuery = new GetMembershipByIdQuery(membershipId);
        var membership = membershipQueryService.handle(getMembershipByIdQuery);
        if (membership.isEmpty()) return ResponseEntity.badRequest().build();
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(membership.get());
        return ResponseEntity.ok(membershipResource);
    }

    @GetMapping
    public ResponseEntity<List<MembershipResource>> getAllMembership(){
        var getAllMembershipsQuery = new GetAllMembershipsQuery();
        var membership = membershipQueryService.handle(getAllMembershipsQuery);
        var membershipResources = membership.stream().map(MembershipResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(membershipResources);
    }


    @PutMapping("/{membershipId}")
    public ResponseEntity<MembershipResource> updateMembership(@PathVariable Long membershipId, @RequestBody UpdateMembershipResource updateMembershipResource){
        var updateMembershipCommand = UpdateMembershipCommandFromResourceAssembler.toCommandFromResource(membershipId, updateMembershipResource);
        var updatedMembership = membershipCommandService.handle(updateMembershipCommand);
        if(updatedMembership.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var membershipResource = MembershipResourceFromEntityAssembler.toResourceFromEntity(updatedMembership.get());
        return ResponseEntity.ok(membershipResource);
    }


    @DeleteMapping("/{membershipId}")
    public ResponseEntity<?> deleteMembership(@PathVariable Long membershipId){
        var deleteMembershipCommand = new DeleteMembershipCommand(membershipId);
        membershipCommandService.handle(deleteMembershipCommand);
        return ResponseEntity.ok("Membership with given id successfully deleted");
    }


}
