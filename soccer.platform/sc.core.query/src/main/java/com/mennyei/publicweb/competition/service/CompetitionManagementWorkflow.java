package com.mennyei.publicweb.competition.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mennyei.core.competition.domain.season.Stage;
import com.mennyei.core.competition.domain.season.Turn;
import com.mennyei.core.competition.events.ClubRegistered;
import com.mennyei.core.competition.events.CompetitionAdded;
import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import com.mennyei.publicweb.competition.dto.competition.CompetitionQuery;
import com.mennyei.publicweb.competition.dto.stage.StageQuery;
import com.mennyei.publicweb.competition.dto.turn.TurnQuery;
import com.mennyei.publicweb.competition.infrastructure.CompetitionQueryMongoRepository;
import com.mennyei.publicweb.match.dto.match.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchQueryMongoRepository;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber
@Component
public class CompetitionManagementWorkflow {

    @Autowired
    private CompetitionQueryMongoRepository competitionMongoRepository;

    @Autowired
    private ClubQueryMongoRepository clubMongoRepository;
    
    @Autowired
    private CompetitionTableService competitionTableService;
    
    @Autowired
    private MatchQueryMongoRepository matchQueryMongoRepository;

    @EventHandlerMethod
    public void create(DispatchedEvent<CompetitionAdded> dispatchedEvent) {
        CompetitionAdded competitionAddedEvent = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        CompetitionQuery competitionQuery = CompetitionQuery.builder()
        		.id(competitionId)
        		.competitionInfo(competitionAddedEvent.getCompetitionInfo())
        		.competitionRuleSet(competitionAddedEvent.getCompetitionRuleSet())
        		.stages(createStageQueries(competitionAddedEvent.getStages()))
        		.build();
        competitionTableService.createTables(competitionQuery);
        competitionMongoRepository.save(competitionQuery);
    }

    private Collection<? extends StageQuery> createStageQueries(List<Stage> stages) {
		return stages.stream().map(this::convertStage).collect(Collectors.toList());
	}
    
    private StageQuery convertStage(Stage stage) {
    	StageQuery stageQuery = StageQuery.builder(stage.getName()).build();
    	stage.getTurns().stream().forEach(turn -> convertTurn(turn, stageQuery));
    	return stageQuery;
    }
    
    private void convertTurn(Turn turn, StageQuery stageQuery) {
    	TurnQuery turnQuery = TurnQuery.builder(turn.getIndex()).build();
    	stageQuery.getTurns().add(turnQuery);
    	convertMatches(turn, turnQuery);
    }
    
    private void convertMatches(Turn turn, TurnQuery turnQuery) {
    	List<MatchQuery> matches = turn.getMatches().stream().map(matchQueryMongoRepository::findOne).collect(Collectors.toList());
    	turnQuery.setMatches(matches);
    }

	@EventHandlerMethod
    public void registerClubs(DispatchedEvent<ClubRegistered> dispatchedEvent) {
        ClubRegistered event = dispatchedEvent.getEvent();
        String competitionId = dispatchedEvent.getEntityId();
        Set<String> clubIds = event.getClubIds();
        CompetitionQuery competitionQuery = competitionMongoRepository.findOne(competitionId);
        List<ClubQuery> clubQueries = clubIds.stream().filter(Objects::nonNull).map(s -> clubMongoRepository.findOne(s)).collect(Collectors.toList());
        competitionQuery.getClubs().addAll(clubQueries);
        competitionMongoRepository.save(competitionQuery);
        competitionTableService.createTableRow(clubQueries, competitionQuery);
    }
    
}