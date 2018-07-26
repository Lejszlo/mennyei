package com.sp.organizer.query.viewer.competition.service;

import com.sp.match.api.filter.MatchDocumentResourceFilter;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.organizer.query.updater.competition.entity.StageDocument;
import com.sp.organizer.query.updater.competition.entity.TurnDocument;
import com.sp.organizer.query.viewer.competition.controller.MatchDocumentQueryFeignQueryController;
import com.sp.organizer.query.viewer.competition.resource.table.TableResource;
import com.sp.organizer.query.viewer.competition.resource.table.TableResourceAssemblerSupport;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableDocumentService {
    private final StageDocumentService stageDocumentService;
    private final TableResourceAssemblerSupport tableResourceAssemblerSupport;
    private final MatchDocumentQueryFeignQueryController matchDocumentQueryFeignQueryController;

    @Autowired
    public TableDocumentService(StageDocumentService stageDocumentService,
                                TableResourceAssemblerSupport tableResourceAssemblerSupport,
                                MatchDocumentQueryFeignQueryController matchDocumentQueryFeignQueryController) {
        this.stageDocumentService = stageDocumentService;
        this.tableResourceAssemblerSupport = tableResourceAssemblerSupport;
        this.matchDocumentQueryFeignQueryController = matchDocumentQueryFeignQueryController;
    }

    public TableResource getTable(String competitionId, String stageId) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageQuery(competitionId, stageId);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(StageDocument::getTurns)
                .orElseGet(Collections::emptyList);

        List<String> matchIds = turnDocuments.stream().flatMap(turnDocument -> turnDocument.getMatcheIds().stream()).collect(Collectors.toList());

        List<MatchDocumentResource> matches = matchDocumentQueryFeignQueryController.getMatches(matchIds);

        return tableResourceAssemblerSupport.toResource(Lists.newArrayList(matches));
    }
}
