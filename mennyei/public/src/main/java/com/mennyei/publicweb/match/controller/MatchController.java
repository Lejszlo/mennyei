package com.mennyei.publicweb.match.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.dto.MatchResource;
import com.mennyei.publicweb.match.dto.MatchResourceAssemblerSupport;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;

@Controller
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchQueryMongoRepository matchMongoRepository;
	
	@RequestMapping(value = "/{clubId}", method = RequestMethod.GET)
	public ResponseEntity<Resources<MatchQuery>> byClub(@PathVariable String clubId) {  
		
		MatchResourceAssemblerSupport matchResourceAssemblerSupport = new MatchResourceAssemblerSupport(clubId);
		
		List<MatchResource> resources = matchResourceAssemblerSupport.toResources(matchMongoRepository.findByClub(clubId));
		return ResponseEntity.ok(resources);
	}
	
	
}
