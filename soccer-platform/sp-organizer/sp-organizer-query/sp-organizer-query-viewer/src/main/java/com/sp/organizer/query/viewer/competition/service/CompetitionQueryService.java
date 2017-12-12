package com.sp.organizer.query.viewer.competition.service;

import java.util.*;

import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionQueryResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.updater.competition.exception.CompetitionNotFoundException;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import com.sp.organizer.query.viewer.competition.resource.competition.CompetitionQueryResource;
import com.sp.organizer.query.viewer.competition.resource.stage.StageQueryResource;
import com.sp.organizer.query.viewer.competition.resource.stage.StageQueryResourceAssemblerSupport;

@Service
public class CompetitionQueryService {
	
	private final CompetitionQueryMongoRepository competitionQueryMongoRepository;

	private final StageQueryResourceAssemblerSupport stageQueryResourceAssemblerSupport;


	@Autowired
	public CompetitionQueryService(CompetitionQueryMongoRepository competitionQueryMongoRepository, StageQueryResourceAssemblerSupport stageQueryResourceAssemblerSupport) {
		this.competitionQueryMongoRepository = competitionQueryMongoRepository;
		this.stageQueryResourceAssemblerSupport = stageQueryResourceAssemblerSupport;
	}

	public CompetitionQueryResource getCompetition(String competitionId) {
		CompetitionQuery competitionQuery = findById(competitionId);
		CompetitionQueryResourceAssemblerSupport resourceAssemblerSupport = new CompetitionQueryResourceAssemblerSupport();
		return resourceAssemblerSupport.toResource(competitionQuery);
	}

	public Collection<StageQueryResource> getStages(String competitionId) {
		CompetitionQuery competition = findById(competitionId);
		List<StageQuery> stages = competition.getStages();
		return stageQueryResourceAssemblerSupport.toResources(stages);
	}

	CompetitionQuery findById(String competitionId) {
		List<CompetitionQuery> findAll = competitionQueryMongoRepository.findAll();
		if(findAll.isEmpty()) {
			throw new CompetitionNotFoundException(competitionId);
		}
		return findAll.get(0);
	}
	
}
