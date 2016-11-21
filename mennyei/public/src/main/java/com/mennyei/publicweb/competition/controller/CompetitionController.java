package com.mennyei.publicweb.competition.controller;

import com.mennyei.core.FillDatabase;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {
	
	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;
	
	@Autowired
	private FillDatabase fillDatabase;

	@GetMapping("/filldatabase")
	public void filldatabase() throws InterruptedException, ExecutionException {
		fillDatabase.fillTestMemoryDB();
	}

	@GetMapping("/{competitionId}")
	public List<CompetitionQuery> getCompetation(@PathVariable("competitionId") String competationId)  {
		return competitionMongoRepository.findAll();
	}
}
