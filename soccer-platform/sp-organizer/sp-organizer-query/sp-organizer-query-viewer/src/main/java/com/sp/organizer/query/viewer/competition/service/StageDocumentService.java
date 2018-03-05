package com.sp.organizer.query.viewer.competition.service;

import com.sp.organizer.query.viewer.club.resource.ClubQueryResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.viewer.club.resource.ClubDocumentResource;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class StageDocumentService {

    private final CompetitionDocumentService competitionDocumentService;

    private final ClubQueryResourceAssemblerSupport clubQueryResourceAssemblerSupport;

    @Autowired
    public StageDocumentService(CompetitionDocumentService competitionDocumentService, ClubQueryResourceAssemblerSupport clubQueryResourceAssemblerSupport) {
        this.competitionDocumentService = competitionDocumentService;
        this.clubQueryResourceAssemblerSupport = clubQueryResourceAssemblerSupport;
    }

    public Resources<ClubDocumentResource> getClubs(String competitionId, int stageIndex) {
        Optional<StageDocument> stageQueryOptional = getStageQuery(competitionId, stageIndex);
        Set<ClubDocument> clubQueries = stageQueryOptional.map(StageDocument::getClubs).orElse(Collections.emptySet());
        return new Resources<>(clubQueryResourceAssemblerSupport.toResources(clubQueries));
    }

    public Optional<StageDocument> getStageQuery(String competitionId, int stageIndex) {
        CompetitionDocument competitionQuery = competitionDocumentService.findById(competitionId);
        return competitionQuery.getStages().stream().filter(stageQuery -> stageQuery.getIndex() == stageIndex).findFirst();
    }
}
