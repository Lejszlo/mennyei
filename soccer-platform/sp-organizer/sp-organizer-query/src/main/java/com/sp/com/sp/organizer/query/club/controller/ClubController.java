package com.sp.organizer.backend.club.controller;


import com.sp.organizer.backend.club.service.ClubQueryService;
import com.sp.core.backend.exception.ClubNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.competition.controller.CompetitionController;
import com.sp.organizer.backend.match.controller.MatchController;

import java.util.Optional;


@RequestMapping("/clubs/")
@RestController
public class ClubController {

	private ClubQueryService clubQueryService;

	@Autowired
	public ClubController(ClubQueryService clubQueryService) {
		this.clubQueryService = clubQueryService;
	}
	
	@RequestMapping(value = "/{urlName}", method = RequestMethod.GET)
	public ResponseEntity<Resource<ClubQuery>> byClub(@PathVariable String urlName) {  
		Optional<ClubQuery> clubQueryOptional = clubQueryService.findClubByUrlName(urlName);

		ClubQuery clubQuery = clubQueryOptional.orElseThrow(ClubNotFoundException::new);
		Resource<ClubQuery> resource = new Resource<>(clubQuery);
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(MatchController.class).byClub(clubQuery.getId())).withRel("matches"));
//		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(ClubController.class).clubPlayers(clubQuery.getId())).withRel("players"));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(CompetitionController.class).getCompetitionsByClub(clubQuery.getId())).withRel("competitions"));
		resource.add(ControllerLinkBuilder.linkTo(ClubController.class).withSelfRel());
		return ResponseEntity.ok(resource);
	}

}
