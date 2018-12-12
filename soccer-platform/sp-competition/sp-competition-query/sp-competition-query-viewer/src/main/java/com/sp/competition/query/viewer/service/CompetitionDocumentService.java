package com.sp.competition.query.viewer.service;

import com.sp.competition.query.updater.entity.CompetitionDocument;
import com.sp.competition.query.updater.entity.SeasonDocument;
import com.sp.competition.query.updater.exception.CompetitionNotFoundException;
import com.sp.competition.query.updater.repository.CompetitionQueryMongoRepository;
import com.sp.competition.query.viewer.resource.SeasonDocumentResourceAssemblerSupport;
import com.sp.competition.query.viewer.resource.competition.CompetitionDocumentResourceAssemblerSupport;
import com.sp.competition.query.viewer.resource.stage.StageDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import sp.competition.api.resource.CompetitionDocumentResource;
import sp.competition.api.resource.SeasonDocumentResource;
import sp.competition.api.resource.StageDocumentResource;
import sp.competition.api.value.SeasonId;

import java.util.Optional;

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
		return Optional.ofNullable(competitionQueryMongoRepository.findOne(competitionId)).orElseThrow(() ->  new CompetitionNotFoundException(competitionId));
	}

	public SeasonDocument findSeason(SeasonId seasonId) {
		CompetitionDocument competitionDocument = findById(seasonId.getCompetitionId().getValue());
		return competitionDocument.
				getSeasons()
				.stream()
				.filter(s -> s.getId().equals(seasonId.getSeasonUuid().toString()))
				.findFirst()
				.orElseThrow(() -> new CompetitionNotFoundException(seasonId.getCompetitionId().getValue())); // TODO másik exception
	}
}
