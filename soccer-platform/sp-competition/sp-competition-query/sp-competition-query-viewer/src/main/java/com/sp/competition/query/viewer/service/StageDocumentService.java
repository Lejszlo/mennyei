package com.sp.competition.query.viewer.service;

import com.sp.competition.query.updater.entity.SeasonDocument;
import com.sp.competition.query.updater.entity.StageDocument;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import sp.competition.api.value.StageId;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class StageDocumentService {

    private final CompetitionDocumentService competitionDocumentService;

    @Autowired
    public StageDocumentService(CompetitionDocumentService competitionDocumentService) {
        this.competitionDocumentService = competitionDocumentService;
    }

    Optional<StageDocument> getStageDocument(StageId stageId) {
        SeasonDocument competitionQuery = competitionDocumentService.findSeason(stageId.getSeasonId());
        return competitionQuery.getStages().stream().filter(stageQuery -> stageQuery.getId().equals(stageId.getStageUuid().toString())).findFirst();
    }
}
