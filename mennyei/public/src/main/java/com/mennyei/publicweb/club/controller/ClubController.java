package com.mennyei.publicweb.club.controller;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchMongoRepository;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;

	@Autowired
	private MatchMongoRepository matchMongoRepository;
	
	@GetMapping("/{clubUrlName}/players")
	public Set<PlayerQuery> getClubPlayers(@PathVariable("clubUrlName") String clubUrlName) throws InterruptedException, ExecutionException {
		ClubQuery clubQuery = clubQueryMongoRepository.findByUrlName(clubUrlName);
		if(clubQuery != null) {
			return clubQuery.getPlayers();
		}
		return Collections.emptySet();
	}
	
	@GetMapping("/{clubId}/{competitionId}/{stageName}/matches")
	public List<MatchQuery> getClubCompetitionMatches(@PathVariable("clubId") String clubId, @PathVariable("competitionId") String competitionId, @PathVariable("stageName") String stageName) {
//		return matchMongoRepository.findClubAndCompetitionAndStageName(clubId, competitionId, "Kelet Magyarország");
		return matchMongoRepository.findAll();
	}
	
}
