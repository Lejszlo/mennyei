package com.sp.organizer.command.aggregator.competition.service;

import com.sp.organizer.api.command.competition.AddClubsCompetitionCommand;
import com.sp.organizer.api.command.competition.AddTurnsCompetitionCommand;
import com.sp.organizer.api.value.competition.season.Turn;
import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import com.sp.organizer.command.aggregator.competition.infrastructure.CompetitionAggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.sp.organizer.api.value.competition.StageId.stageId;

@Service
public class StageService {

    private final CompetitionAggregateRepository competitionAggregateRepository;

    @Autowired
    public StageService(CompetitionAggregateRepository competitionAggregateRepository) {
        this.competitionAggregateRepository = competitionAggregateRepository;
    }

    public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addClubsToStage(String competitionId, UUID stageId, Collection<String> clubIds) {
        return competitionAggregateRepository.update(competitionId, AddClubsCompetitionCommand.builder()
                .clubIds(clubIds)
                .stageId(stageId(competitionId, stageId))
                .build());
    }

    public CompletableFuture<EntityWithIdAndVersion<CompetitionAggregate>> addTurnsToStage(String competitionId, UUID stageId, Collection<Turn> turnIds) {
        return competitionAggregateRepository.update(competitionId.toString(), AddTurnsCompetitionCommand.builder()
                .turnIds(turnIds)
                .stageId(stageId(competitionId, stageId))
                .build());
    }

}
