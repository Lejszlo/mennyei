package com.sp.organizer.command.aggregator.competition.validation;

import com.google.common.collect.Sets;
import com.sp.organizer.api.command.competition.AddClubs;
import com.sp.organizer.api.value.competition.StageId;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.command.aggregator.competition.domain.CompetitionAggregate;
import org.springframework.stereotype.Service;

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
