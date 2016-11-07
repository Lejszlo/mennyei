package com.mennyei.publicweb.controller.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.core.team.domain.Team;
import com.mennyei.core.team.service.TeamRepository;

@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@GetMapping("/{clubId}")
	public Team getClubInfos(Long id) {
		return teamRepository.findById(id);
	}
	
}
