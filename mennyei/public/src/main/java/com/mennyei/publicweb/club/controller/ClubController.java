package com.mennyei.publicweb.club.controller;

import com.mennyei.core.FillDatabase;
import com.mennyei.core.club.domain.ClubInfo;
import com.mennyei.publicweb.club.dto.PlayerMatchStatisticData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ExecutionException;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	
	@Autowired
	private FillDatabase fillDatabase;
	
	
	@GetMapping("/{clubId}/players")
	public Set<PlayerMatchStatisticData> getClubPlayers(@PathVariable("clubId") ClubInfo clubInfo) throws InterruptedException, ExecutionException {
		return null;
	}
	
}
