package com.hajdu.sp.competition.update.validation;
import com.google.common.collect.Sets;
import com.hajdu.sp.club.lib.value.ClubId;
import com.hajdu.sp.competition.lib.command.AddClubs;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.lib.value.season.Stage;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
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

        Set<ClubId> newClubIds = addClubs.getClubIds();

        if( !Sets.intersection(newClubIds, stage.getClubIds()).isEmpty()) {
            throw new RuntimeException(); // TODO bad request
        }

        int numberOfTeams = stage.getStageRuleSet().getNumberOfTeams();

        if(numberOfTeams < stage.getClubIds().size() + newClubIds.size()) {
            throw new RuntimeException(); // TODO bad request
        }
    }

}
