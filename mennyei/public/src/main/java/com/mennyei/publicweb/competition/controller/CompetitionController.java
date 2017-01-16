package com.mennyei.publicweb.competition.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.core.FillDatabase;
import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.table.TableQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionMongoRepository;
import com.mennyei.publicweb.competition.service.CompetitionMatchService;
import com.mennyei.publicweb.competition.service.CompetitionTableService;
import com.mennyei.publicweb.match.dto.MatchDetailesQuery;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {

	@Autowired
	private CompetitionMongoRepository competitionMongoRepository;

	@Autowired
	private CompetitionTableService competitionTableService;

	@Autowired
	private CompetitionMatchService competitionMatchService;

	@Autowired
	private FillDatabase fillDatabase;

	@GetMapping("/filldatabase")
	public void filldatabase() throws InterruptedException, ExecutionException {
		fillDatabase.fillTestMemoryDB();
	}

	@GetMapping("/{competitionId}")
	public List<CompetitionQuery> getCompetation(@PathVariable("competitionId") String competationId) {
		return competitionMongoRepository.findAll();
	}

	@GetMapping("/{competitionId}/{stageName}/table")
	public TableQuery getCompetitionTable(@PathVariable("competitionId") String competitionId, @PathVariable("stageName") String stageName) {
		return competitionTableService.getCompetationTable(competitionId, "Kelet Magyarország");
	}

	@GetMapping("/{matchId}")
	public MatchDetailesQuery getCompetitionMatchDetails(@PathVariable("matchId") String matchId) {
		return competitionMatchService.getCompetationMatchDetailes(matchId);
	}

}
