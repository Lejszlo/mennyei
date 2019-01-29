package com.hajdu.sp.competition.query.service;

import com.hajdu.sp.competition.lib.resource.CompetitionDocumentResource;
import com.hajdu.sp.competition.lib.resource.SeasonDocumentResource;
import com.hajdu.sp.competition.lib.resource.StageDocumentResource;
import com.hajdu.sp.competition.lib.value.SeasonId;
import com.hajdu.sp.competition.query.entity.CompetitionDocument;
import com.hajdu.sp.competition.query.entity.SeasonDocument;
import com.hajdu.sp.competition.query.repository.CompetitionQueryMongoRepository;
import com.hajdu.sp.competition.query.resource.SeasonDocumentResourceAssemblerSupport;
import com.hajdu.sp.competition.query.resource.competition.CompetitionDocumentResourceAssemblerSupport;
import com.hajdu.sp.competition.query.resource.stage.StageDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CompetitionDocumentService {
	private final CompetitionQueryMongoRepository competitionQueryMongoRepository;
	private final StageDocumentResourceAssemblerSupport stageDocumentResourceAssemblerSupport;
	private final CompetitionDocumentResourceAssemblerSupport competitionDocumentResourceAssemblerSupport;
	private SeasonDocumentResourceAssemblerSupport seasonDocumentResourceAssemblerSupport;

	@Autowired
	public CompetitionDocumentService(CompetitionQueryMongoRepository competitionQueryMongoRepository,
									  StageDocumentResourceAssemblerSupport stageDocumentResourceAssemblerSupport,
									  CompetitionDocumentResourceAssemblerSupport competitionDocumentResourceAssemblerSupport,
									  SeasonDocumentResourceAssemblerSupport seasonDocumentResourceAssemblerSupport) {
		this.competitionQueryMongoRepository = competitionQueryMongoRepository;
		this.stageDocumentResourceAssemblerSupport = stageDocumentResourceAssemblerSupport;
        this.competitionDocumentResourceAssemblerSupport = competitionDocumentResourceAssemblerSupport;
		this.seasonDocumentResourceAssemblerSupport = seasonDocumentResourceAssemblerSupport;
	}

	public Resources<CompetitionDocumentResource> getCompetitions() {
		return new Resources<>(competitionDocumentResourceAssemblerSupport.toResources(competitionQueryMongoRepository.findAll()));
	}

	public CompetitionDocumentResource getCompetition(String competitionId) {
		CompetitionDocument competitionQuery = findById(competitionId);
		return competitionDocumentResourceAssemblerSupport.toResource(competitionQuery);
	}

	public Resources<SeasonDocumentResource> getSeasons(String competitionId) {
		return new Resources<>(seasonDocumentResourceAssemblerSupport.toResources(findById(competitionId).getSeasons()));
	}

	public Resources<StageDocumentResource> getStages(SeasonId seasonId) {
		return new Resources<>(stageDocumentResourceAssemblerSupport.toResources(findSeason(seasonId).getStages()));
	}

	public CompetitionDocument findById(String competitionId) {
		return competitionQueryMongoRepository.findById(competitionId).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, competitionId));
	}

	public SeasonDocument findSeason(SeasonId seasonId) {
		CompetitionDocument competitionDocument = findById(seasonId.getCompetitionId().getValue());
		return competitionDocument.
				getSeasons()
				.stream()
				.filter(s -> s.getId().equals(seasonId.getSeasonUuid().toString()))
				.findFirst()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, seasonId.getCompetitionId().getValue()));
	}
}
