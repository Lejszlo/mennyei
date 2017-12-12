package com.sp.organizer.query.viewer.club.controller;

import com.sp.organizer.query.viewer.club.resource.ClubQueryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sp.organizer.query.viewer.club.service.ClubQueryService;


@RequestMapping("/club/")
@RestController
public class ClubQueryController {

	private ClubQueryService clubQueryService;

	@Autowired
	public ClubQueryController(ClubQueryService clubQueryService) {
		this.clubQueryService = clubQueryService;
	}
	
	@RequestMapping(value = "/{urlName}", method = RequestMethod.GET)
	public ResponseEntity<Resource<ClubQueryResource>> byClub(@PathVariable String urlName) {
//		Optional<ClubQuery> clubQueryOptional = clubQueryService.findClubByUrlName(urlName);
//
//		ClubQuery clubQuery = clubQueryOptional.orElseThrow(ClubNotFoundException::new);
//		Resource<ClubQuery> resource = new Resource<>(clubQuery);
//		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MatchController.class).byClub(clubQuery.getId())).withRel("matches"));
//		resource.add(ControllerLinkBuilder.linkTo(ClubQueryController.class).withSelfRel());
//		return ResponseEntity.ok(resource);
		return null;
	}

}
