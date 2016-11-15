package com.mennyei.publicweb.club.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.core.FillDatabase;
import com.mennyei.core.competition.domain.Competition;
import com.mennyei.core.team.domain.Club;
import com.mennyei.publicweb.club.dto.PlayerMatchStatisticData;
import com.mennyei.publicweb.club.service.ClubService;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private FillDatabase fillDatabase;
	
	
	@GetMapping("/{clubId}/players")
	public Set<PlayerMatchStatisticData> getClubPlayers(@PathVariable("clubId") Club club, @PathVariable("competationId") Competition competation) {
		
		fillDatabase.fillTestMemoryDB();
		
		return clubService.getClubPlayersWithMatchStatisticDatas(club, competation);
	}
	
}
