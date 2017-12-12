package com.sp.organizer.query.updater.competition.handler;

import com.sp.organizer.query.updater.club.entity.ClubQuery;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;
import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;
import com.sp.organizer.query.updater.competition.entity.TurnQuery;
import com.sp.organizer.query.updater.competition.repository.CompetitionQueryMongoRepository;
import com.sp.organizer.query.updater.competition.service.CompetitionTableService;
import com.sp.organizer.query.updater.match.entity.MatchQuery;
import event.competition.CompetitionAdded;
import event.competition.StageAdded;
import io.eventuate.DispatchedEvent;
import com.sp.organizer.query.updater.competition.entity.StageQuery;
import com.sp.organizer.query.updater.match.repository.MatchQueryMongoRepository;
import value.competition.season.Stage;
import value.competition.season.Turn;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@EventSubscriber(id = "competitionEventHandler", progressNotifications = true)
@Component
public class CompetitionEventHandler {

    private final CompetitionQueryMongoRepository competitionMongoRepository;

    private final ClubQueryMongoRepository clubMongoRepository;
    
    private final CompetitionTableService competitionTableService;
    
    private final MatchQueryMongoRepository matchQueryMongoRepository;

    @Autowired
    public CompetitionEventHandler(CompetitionQueryMongoRepository competitionMongoRepository,
                                   ClubQueryMongoRepository clubMongoRepository,
                                   CompetitionTableService competitionTableService,
                                   MatchQueryMongoRepository matchQueryMongoRepository) {
        this.competitionMongoRepository = competitionMongoRepository;
        this.clubMongoRepository = clubMongoRepository;
        this.competitionTableService = competitionTableService;
        this.matchQueryMongoRepository = matchQueryMongoRepository;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded competitionAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionQuery competitionQuery = CompetitionQuery.builder(competitionAddedEvent.getCompetitionInfo())
        		.id(competitionId)
                .stages(Lists.newArrayList())
        		.build();
        competitionMongoRepository.save(competitionQuery);
    }

    @EventHandlerMethod
    public void stageAdded(DispatchedEvent<StageAdded> stageAdded) {
        StageAdded stageAddedEvent = stageAdded.getEvent();
        String competitionId = stageAdded.getEntityId();
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);

        Set<ClubQuery> clubQueries = stageAddedEvent
                .getStage()
                .getClubIds()
                .stream()
                .map(clubMongoRepository::findOne)
                .collect(Collectors.toSet());

        StageQuery stageQuery = convertStage(clubQueries, stageAddedEvent.getStage(), competitionId);
        competitionQuery.getStages().add(stageQuery);

        competitionTableService.createTables(stageQuery);

        competitionMongoRepository.save(competitionQuery);
    }

    private StageQuery convertStage(Set<ClubQuery> clubQueries, Stage stage, String competitionId) {
        List<TurnQuery> turnQueries = stage.getTurns().stream().map(this::convertTurn).collect(Collectors.toList());
        return StageQuery
                .builder(stage.getName(), stage.getStageRuleSet(), stage.getIndex())
                .competitionQuery(competitionId)
                .turns(turnQueries)
                .clubs(clubQueries)
                .build();
    }
    
    private TurnQuery convertTurn(Turn turn) {
        List<MatchQuery> matchQueries = turn.getMatches().stream().map(s -> matchQueryMongoRepository.findOne(s)).collect(Collectors.toList());
        return TurnQuery.builder(turn.getIndex())
                .matches(matchQueries)
                .build();
    }
}