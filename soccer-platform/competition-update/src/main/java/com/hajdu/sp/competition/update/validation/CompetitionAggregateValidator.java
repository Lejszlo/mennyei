package com.hajdu.sp.competition.update.validation;

import com.hajdu.sp.competition.update.command.competition.AddClub;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
import com.hajdu.sp.competition.update.exceptions.ExceptionValue;
import com.hajdu.sp.competition.update.exceptions.InvariantException;
import com.hajdu.sp.competition.update.exceptions.NotFoundException;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import org.springframework.stereotype.Service;

@Service
public class CompetitionAggregateValidator {

    public static void check(CompetitionAggregate competitionAggregate, AddClub addClub) {
        Stage stage = checkStage(competitionAggregate, addClub);

        checkExistingClubs(addClub, stage);

        checkNumberOfClubs(addClub.getClubId(), stage);
    }

    private static Stage checkStage(CompetitionAggregate competitionAggregate, AddClub addClub) {
        StageId stageId = addClub.getStageId();

        return competitionAggregate.getSeasons()
                .findStageById(addClub.getStageId())
                .orElseThrow(() -> new NotFoundException(stageId.toString()));
    }

    private static void checkExistingClubs(AddClub addClub, Stage stage) {
        if(stage.getClubIds().contains(addClub.getClubId())) {
            throw new InvariantException("The club has been already added", ExceptionValue.builder().value(addClub.getClubId().getId()).build());
        }
    }

    private static void checkNumberOfClubs(ClubId newClubId, Stage stage) {
        int numberOfClubs = stage.getStageRuleSet().getNumberOfTeams();
        int newNumberOfClubs = stage.getClubIds().size() + 1;
        if(numberOfClubs < newNumberOfClubs) {
            throw new InvariantException("The number of clubs has reached the maximum", ExceptionValue.builder().value(newClubId).build());
        }
    }
}
