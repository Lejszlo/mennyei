package com.sp.organizer.backend.competition.controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;
import com.sp.organizer.backend.competition.dto.competition.CompetitionResource;
import com.sp.organizer.backend.competition.dto.competition.CompetitionResourceAssemblerSupport;
import com.sp.organizer.backend.competition.dto.table.TableQuery;
import com.sp.organizer.backend.competition.infrastructure.CompetitionNotFoundException;
import com.sp.organizer.backend.competition.service.CompetitionQueryService;
import com.sp.organizer.backend.competition.service.CompetitionTableService;
import com.sp.organizer.backend.util.FillOrganizerDatabase;

@RequestMapping("/competition/")
@RestController
public class CompetitionController {

	@Autowired
	private CompetitionTableService competitionTableService;

	@Autowired
	private CompetitionQueryService competitionQueryService;

	@Autowired
	private FillOrganizerDatabase fillDatabase;

	@GetMapping("/filldatabase")
	public void filldatabase() throws InterruptedException, ExecutionException {
		fillDatabase.fillTestMemoryDB();
	}

	@GetMapping("/{competitionId}")
	public ResponseEntity<CompetitionResource> getCompetation(@PathVariable("competitionId") String competitionId) {
		Optional<CompetitionQuery> competitionQueryOptional = competitionQueryService.findCompetition(competitionId);
		CompetitionQuery competitionQuery = competitionQueryOptional.orElseThrow(() -> new CompetitionNotFoundException(competitionId));
		CompetitionResourceAssemblerSupport resourceAssemblerSupport = new CompetitionResourceAssemblerSupport();
		CompetitionResource resource = resourceAssemblerSupport.toResource(competitionQuery);
		return ResponseEntity.ok(resource);
	}

	@RequestMapping(value = "/{clubId}/related", method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource<CompetitionResource>>> getCompetitionsByClub(@PathVariable("clubId") String clubId) {
		 List<CompetitionQuery> competitionQueries = competitionQueryService.findCompetitionsByClub(clubId);
		 CompetitionResourceAssemblerSupport resourceAssemblerSupport = new CompetitionResourceAssemblerSupport();
		 List<CompetitionResource> resources = resourceAssemblerSupport.toResources(competitionQueries);
		 return ResponseEntity.ok(Resources.wrap(resources));
	}
	
	@RequestMapping(value = "/{competitionId}/tables", method = RequestMethod.GET)
	public ResponseEntity<Resources<Resource<TableQuery>>> getCompetitionTables(@PathVariable("competitionId") String competitionId) {
		List<TableQuery> tableQueries = competitionTableService.getCompetationTable(competitionId);
		return ResponseEntity.ok(Resources.wrap(tableQueries));
	}

}
