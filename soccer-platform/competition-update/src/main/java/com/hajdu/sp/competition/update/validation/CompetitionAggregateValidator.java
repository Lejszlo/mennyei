package com.hajdu.sp.competition.update.validation;

import com.hajdu.sp.competition.update.command.competition.AddClub;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
import com.hajdu.sp.competition.update.exceptions.ExceptionValue;
import com.hajdu.sp.competition.update.exceptions.InvariantException;
import com.hajdu.sp.competition.update.exceptions.NotFoundException;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.season.Season;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompetitionAggregateValidator implements Invariant<CompetitionAggregate, AddClub> {

    @Override
    public void check(CompetitionAggregate competitionAggregate, AddClub addClub) {
        Stage stage = checkStage(competitionAggregate, addClub);

        checkExistingClubs(addClub, stage);

        checkNumberOfClubs(addClub.getClub(), stage);
    }

    private Stage checkStage(CompetitionAggregate competitionAggregate, AddClub addClub) {
        StageId stageId = addClub.getStageId();

        Season latestSeason = competitionAggregate.getSeasons()
                .findLatestSeason(LocalDateTime.now())
                .orElseThrow(() -> new NotFoundException("Latest season"));

        return latestSeason.getStages()
                .getByStageId(stageId)
                .orElseThrow(() -> new NotFoundException(stageId.getSeasonId().getSeasonUuid().toString()));
    }

    private void checkExistingClubs(AddClub addClub, Stage stage) {
        if(stage.getClubIds().contains(addClub.getClub())) {
            throw new InvariantException("The club has been already added", ExceptionValue.builder().value(addClub.getClub().getId()).build());
        }
    }

    private void checkNumberOfClubs(ClubId newClubId, Stage stage) {
        int numberOfClubs = stage.getStageRuleSet().getNumberOfTeams();
        int newNumberOfClubs = stage.getClubIds().size() + 1;
        if(numberOfClubs < newNumberOfClubs) {
            throw new InvariantException("The number of clubs has reached the maximum", ExceptionValue.builder().value(newNumberOfClubs).build());
        }
    }
}
