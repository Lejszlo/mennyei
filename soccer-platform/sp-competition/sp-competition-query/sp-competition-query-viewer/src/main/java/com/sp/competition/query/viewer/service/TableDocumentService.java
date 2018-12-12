package com.sp.competition.query.viewer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sp.competition.query.updater.entity.StageDocument;
import com.sp.competition.query.updater.entity.TurnDocument;
import com.sp.competition.query.viewer.controller.MatchDocumentQueryFeignQueryController;
import com.sp.competition.query.viewer.resource.table.TableResource;
import com.sp.competition.query.viewer.resource.table.TableResourceAssemblerSupport;
import com.sp.match.api.resource.MatchDocumentResource;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sp.competition.api.value.StageId;

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

    @HystrixCommand
    public TableResource getTable(StageId stageId) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageDocument(stageId);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(StageDocument::getTurns)
                .orElseGet(Collections::emptyList);

        List<String> matchIds = turnDocuments.stream().flatMap(turnDocument -> turnDocument.getMatcheIds().stream()).collect(Collectors.toList());

        List<MatchDocumentResource> matches = matchDocumentQueryFeignQueryController.getMatches(matchIds);

        return tableResourceAssemblerSupport.toResource(Lists.newArrayList(matches));
    }
}
