package com.hajdu.sp.competition.query.service;

import com.google.common.collect.Lists;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.query.controller.MatchDocumentQueryFeignController;
import com.hajdu.sp.competition.query.entity.StageDocument;
import com.hajdu.sp.competition.query.entity.TurnDocument;
import com.hajdu.sp.competition.query.resource.table.TableResource;
import com.hajdu.sp.competition.query.resource.table.TableResourceAssemblerSupport;
import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TableDocumentService {
    private final StageDocumentService stageDocumentService;
    private final TableResourceAssemblerSupport tableResourceAssemblerSupport;
    private final MatchDocumentQueryFeignController matchDocumentQueryFeignController;

    @Autowired
    public TableDocumentService(StageDocumentService stageDocumentService,
                                TableResourceAssemblerSupport tableResourceAssemblerSupport,
                                MatchDocumentQueryFeignController matchDocumentQueryFeignController) {
        this.stageDocumentService = stageDocumentService;
        this.tableResourceAssemblerSupport = tableResourceAssemblerSupport;
        this.matchDocumentQueryFeignController = matchDocumentQueryFeignController;
    }

    @HystrixCommand
    public TableResource getTable(StageId stageId) {
        Optional<StageDocument> stageDocumentOptional = stageDocumentService.getStageDocument(stageId);
        List<TurnDocument> turnDocuments = stageDocumentOptional.map(StageDocument::getTurns)
                .orElseGet(Collections::emptyList);

        List<String> matchIds = turnDocuments.stream().flatMap(turnDocument -> turnDocument.getMatcheIds().stream()).collect(Collectors.toList());

        List<MatchDocumentResource> matches = matchDocumentQueryFeignController.getMatches(matchIds);

        return tableResourceAssemblerSupport.toResource(Lists.newArrayList(matches));
    }
}
