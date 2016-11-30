package com.mennyei.publicweb.club.controller;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;
	
	@GetMapping("/{clubUrlName}/players")
	public Set<PlayerQuery> getClubPlayers(@PathVariable("clubUrlName") String clubUrlName) throws InterruptedException, ExecutionException {
		return clubQueryMongoRepository.findByUrlName(clubUrlName).getPlayers();
	}
	
}
