package com.example.backend_one.TelefonbokenRESTAPI;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class FriendModelAssembler implements RepresentationModelAssembler<Friend, EntityModel<Friend>> {

    @Override
    public EntityModel<Friend> toModel(Friend friend) {

        return EntityModel.of(friend, //
                linkTo(methodOn(FriendHATEOASController.class).one((long) friend.getId())).withSelfRel(),
                linkTo(methodOn(FriendHATEOASController.class).all()).withRel("employees"));
    }
}