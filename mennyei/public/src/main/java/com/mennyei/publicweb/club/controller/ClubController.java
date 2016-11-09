package com.mennyei.publicweb.club.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.core.FillDatabase;
import com.mennyei.publicweb.club.dto.PlayerWithStats;
import com.mennyei.publicweb.club.service.ClubService;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private FillDatabase fillDatabase;
	
	
	@GetMapping("/{clubId}/players")
	public Set<PlayerWithStats> getClubPlayers(@PathVariable Long clubId) {
		
		fillDatabase.fillTeam();
		
		return clubService.getClubPlayers(clubId);
	}
	
}
