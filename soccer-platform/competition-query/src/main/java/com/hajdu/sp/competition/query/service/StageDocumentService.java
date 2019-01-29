package com.hajdu.sp.competition.query.service;

import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.query.entity.SeasonDocument;
import com.hajdu.sp.competition.query.entity.StageDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
