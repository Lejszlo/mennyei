package com.sp.organizer.query.viewer.competition.service;

import java.util.*;

import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionDocumentResourceAssemblerSupport;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.turn.TurnDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.updater.competition.exception.CompetitionNotFoundException;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.stage.StageDocumentResource;
import com.sp.organizer.query.viewer.competition.resource.stage.StageQueryResourceAssemblerSupport;

@Service
public class CompetitionDocumentService {
	private final CompetitionQueryMongoRepository competitionQueryMongoRepository;
	private final StageQueryResourceAssemblerSupport stageQueryResourceAssemblerSupport;
	private final CompetitionDocumentResourceAssemblerSupport competitionDocumentResourceAssemblerSupport;

	@Autowired
	public CompetitionDocumentService(CompetitionQueryMongoRepository competitionQueryMongoRepository,
                                      StageQueryResourceAssemblerSupport stageQueryResourceAssemblerSupport,
                                      CompetitionDocumentResourceAssemblerSupport competitionDocumentResourceAssemblerSupport) {
		this.competitionQueryMongoRepository = competitionQueryMongoRepository;
		this.stageQueryResourceAssemblerSupport = stageQueryResourceAssemblerSupport;
        this.competitionDocumentResourceAssemblerSupport = competitionDocumentResourceAssemblerSupport;
    }

	public CompetitionDocumentResource getCompetition(String competitionId) {
		CompetitionDocument competitionQuery = findById(competitionId);
		return competitionDocumentResourceAssemblerSupport.toResource(competitionQuery);
	}

	public Resources<StageDocumentResource> getStages(String competitionId) {
        return new Resources<>(stageQueryResourceAssemblerSupport.toResources(findById(competitionId).getStages()));
    }

	CompetitionDocument findById(String competitionId) {
		List<CompetitionDocument> findAll = competitionQueryMongoRepository.findAll();
		if(findAll.isEmpty()) {
			throw new CompetitionNotFoundException(competitionId);
		}
		return findAll.get(0);
	}

	public Resources<CompetitionDocumentResource> getCompetitions() {
		return new Resources<>(competitionDocumentResourceAssemblerSupport.toResources(competitionQueryMongoRepository.findAll()));
	}
}
