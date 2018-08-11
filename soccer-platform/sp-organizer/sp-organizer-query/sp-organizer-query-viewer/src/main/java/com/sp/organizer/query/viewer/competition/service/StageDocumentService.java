package com.sp.organizer.query.viewer.competition.service;

import com.sp.organizer.query.viewer.club.resource.ClubDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.organizer.query.updater.competition.entity.CompetitionDocument;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.api.resource.ClubDocumentResource;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class StageDocumentService {

    private final CompetitionDocumentService competitionDocumentService;

    private final ClubDocumentResourceAssemblerSupport clubDocumentResourceAssemblerSupport;

    @Autowired
    public StageDocumentService(CompetitionDocumentService competitionDocumentService, ClubDocumentResourceAssemblerSupport clubDocumentResourceAssemblerSupport) {
        this.competitionDocumentService = competitionDocumentService;
        this.clubDocumentResourceAssemblerSupport = clubDocumentResourceAssemblerSupport;
    }

    public Resources<ClubDocumentResource> getClubs(String competitionId, String stageId) {
        Optional<StageDocument> stageQueryOptional = getStageDocument(competitionId, stageId);
        Set<ClubDocument> clubQueries = stageQueryOptional.map(StageDocument::getClubs).orElse(Collections.emptySet());
        return new Resources<>(clubDocumentResourceAssemblerSupport.toResources(clubQueries));
    }

    Optional<StageDocument> getStageDocument(String competitionId, String stageId) {
        CompetitionDocument competitionQuery = competitionDocumentService.findById(competitionId);
        return competitionQuery.getStages().stream().filter(stageQuery -> stageQuery.getId().equals(stageId)).findFirst();
    }
}
