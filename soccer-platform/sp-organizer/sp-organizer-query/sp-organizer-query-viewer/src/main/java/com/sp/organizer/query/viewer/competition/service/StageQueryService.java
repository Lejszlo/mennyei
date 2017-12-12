package com.sp.organizer.query.viewer.competition.service;

import com.sp.organizer.query.viewer.club.resource.ClubQueryResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.club.entity.ClubQuery;
import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.viewer.club.resource.ClubQueryResource;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class StageQueryService {

    private final CompetitionQueryService competitionQueryService;

    private final ClubQueryResourceAssemblerSupport clubQueryResourceAssemblerSupport;

    @Autowired
    public StageQueryService(CompetitionQueryService competitionQueryService, ClubQueryResourceAssemblerSupport clubQueryResourceAssemblerSupport) {
        this.competitionQueryService = competitionQueryService;
        this.clubQueryResourceAssemblerSupport = clubQueryResourceAssemblerSupport;
    }

    public Collection<ClubQueryResource> getClubs(String competitionId, int stageIndex) {
        Optional<StageQuery> stageQueryOptional = getStageQuery(competitionId, stageIndex);
        Set<ClubQuery> clubQueries = stageQueryOptional.map(StageQuery::getClubs).orElse(Collections.emptySet());
        return clubQueryResourceAssemblerSupport.toResources(clubQueries);
    }

    public Optional<StageQuery> getStageQuery(String competitionId, int stageIndex) {
        CompetitionQuery competitionQuery = competitionQueryService.findById(competitionId);
        return competitionQuery.getStages().stream().filter(stageQuery -> stageQuery.getIndex() == stageIndex).findFirst();
    }
}
