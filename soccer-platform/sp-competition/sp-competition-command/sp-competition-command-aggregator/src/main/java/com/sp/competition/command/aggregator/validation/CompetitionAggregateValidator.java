package com.sp.competition.command.aggregator.validation;

import com.google.common.collect.Sets;
import com.sp.competition.command.aggregator.domain.CompetitionAggregate;
import org.springframework.stereotype.Service;
import sp.competition.api.command.AddClubs;
import sp.competition.api.value.StageId;
import sp.competition.api.value.season.Stage;

import java.util.Set;

@Service
public class CompetitionAggregateValidator {

    public static void checkClubsInvariant(CompetitionAggregate aggregate, AddClubs addClubs) {
        StageId stageId = addClubs.getStageId();

        Stage stage = aggregate.getSeasons()
                .stream()
                .flatMap(s -> s.getStages()
                        .stream())
                .filter(s -> s.getId().equals(stageId))
                .findFirst()
                .orElseThrow(RuntimeException::new); //TODO bad request

        Set<String> newClubIds = addClubs.getClubIds();


        if(!Sets.intersection(newClubIds, stage.getClubIds()).isEmpty()) {
            throw new RuntimeException(); // TODO bad request
        }

        int numberOfTeams = stage.getStageRuleSet().getNumberOfTeams();

        if(numberOfTeams < stage.getClubIds().size() + newClubIds.size()) {
            throw new RuntimeException(); // TODO bad request
        }
    }

}
