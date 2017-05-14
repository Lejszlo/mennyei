package com.sp.organizer.backend.match.controller;

import java.util.List;

import com.sp.organizer.backend.match.dto.match.MatchQuery;
import com.sp.organizer.backend.match.resource.MatchDetailesResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.organizer.backend.match.resource.MatchResource;
import com.sp.organizer.backend.match.resource.MatchResourceAssemblerSupport;
import com.sp.organizer.backend.match.infrastructure.MatchQueryMongoRepository;
import com.sp.organizer.backend.match.service.MatchDetailesService;

@Controller
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	@Autowired
	private MatchDetailesService matchDetailesService;
	
	@RequestMapping(value = "/{clubId}", method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource<MatchResource>>> byClub(@PathVariable String clubId) {
		MatchResourceAssemblerSupport matchResourceAssemblerSupport = new MatchResourceAssemblerSupport(clubId);
		List<MatchQuery> matches = matchMongoRepository.findByClubOrderByMatchDate(clubId);
		List<MatchResource> resources = matchResourceAssemblerSupport.toResources(matches);
		return ResponseEntity.ok(Resources.wrap(resources));
	}

	@RequestMapping(value = "/{matchId}/detailes", method = RequestMethod.GET)
	public ResponseEntity<MatchDetailesResource> byClubWithDetailes(@PathVariable String matchId) {
        return ResponseEntity.ok(matchDetailesService.getPlayerMatchDetailes(matchId));
	}

}
