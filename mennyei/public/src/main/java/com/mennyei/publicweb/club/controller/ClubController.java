package com.mennyei.publicweb.club.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.publicweb.club.dto.PlayerQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.MatchQuery;
import com.mennyei.publicweb.competition.service.CompetitionMatchService;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private ClubQueryMongoRepository clubQueryMongoRepository;
	
	@Autowired
	private CompetitionMatchService competitionBusinessService;
	
	@GetMapping("/{clubUrlName}/players")
	public Set<PlayerQuery> getClubPlayers(@PathVariable("clubUrlName") String clubUrlName) throws InterruptedException, ExecutionException {
		return clubQueryMongoRepository.findByUrlName(clubUrlName).getPlayers();
	}
	
	@GetMapping("/{clubId}/{competitionId}/{stageName}/matches")
	public List<MatchQuery> getClubCompetitionMatches(@PathVariable("clubId") String clubId, @PathVariable("competitionId") String competitionId, @PathVariable("stageName") String stageName) {
		return competitionBusinessService.getClubMatches(clubId, competitionId, "Kelet Magyarország");
	}
	
}
