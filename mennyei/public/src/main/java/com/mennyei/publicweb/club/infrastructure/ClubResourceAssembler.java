package com.mennyei.publicweb.club.infrastructure;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.match.controller.MatchController;

public class ClubResourceAssembler extends ResourceAssemblerSupport<ClubQuery, ClubResource> {

	public ClubResourceAssembler(Class<?> controllerClass, Class<ClubResource> resourceType) {
		super(controllerClass, resourceType);
	}

	@Override
	public ClubResource toResource(ClubQuery entity) {
		ClubResource clubResource = createResourceWithId(entity.getId(), entity);
		clubResource.add(ControllerLinkBuilder.linkTo(MatchController.class).withRel("club"));
		return clubResource;
	}

}
