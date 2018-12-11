package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.api.command.competition.AddClubs;
import com.sp.organizer.api.command.competition.AddTurns;
import com.sp.organizer.api.value.competition.season.Turn;
import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class StageService {

    private final CompetitionAggregateRepository competitionAggregateRepository;

    @Autowired
    public StageService(CompetitionAggregateRepository competitionAggregateRepository) {
        this.competitionAggregateRepository = competitionAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addClubsToStage(String competitionId, UUID stageId, Collection<String> clubIds) {
        return competitionAggregateRepository.update(competitionId, AddClubs.builder()
                .clubIds(clubIds)
//                .stageId(stageId(competitionId, stageId))
                .build());
    }

    public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addTurnsToStage(String competitionId, UUID stageId, Collection<Turn> turnIds) {
        return competitionAggregateRepository.update(competitionId.toString(), AddTurns.builder()
                .turnIds(turnIds)
//                .stageId(stageId(competitionId(competitionId), stageId))
                .build());
    }

}
