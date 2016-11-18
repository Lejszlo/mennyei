package com.mennyei.publicweb.competition.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.core.FillDatabase;
import com.mennyei.publicweb.competition.dto.CompetitionClubListQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;
	
	@Autowired
	private FillDatabase fillDatabase;

	@GetMapping("/{competitionId}/")
	public CompetitionClubListQuery getCompetation(@PathVariable("competitionId") Long competationId) throws InterruptedException, ExecutionException {
		fillDatabase.fillTestMemoryDB();
		return competitionMongoRepository.findAll().get(0);
	}
}
