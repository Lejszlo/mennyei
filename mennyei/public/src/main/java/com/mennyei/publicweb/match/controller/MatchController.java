package com.mennyei.publicweb.match.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mennyei.publicweb.match.dto.detailes.MatchDetailesResource;
import com.mennyei.publicweb.match.dto.match.MatchResource;
import com.mennyei.publicweb.match.dto.match.MatchResourceAssemblerSupport;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;
import com.mennyei.publicweb.match.service.MatchDetailesService;

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
		List<MatchResource> resources = matchResourceAssemblerSupport.toResources(matchMongoRepository.findByClubOrderByMatchDate(clubId));
		return ResponseEntity.ok(Resources.wrap(resources));
	}
	
	@RequestMapping(value = "/{matchId}/detailes", method = RequestMethod.GET)
	public ResponseEntity<MatchDetailesResource> byClubWithDetailes(@PathVariable String matchId) {
		return ResponseEntity.ok(matchDetailesService.matchDetailes(matchId));
	}
	
}
