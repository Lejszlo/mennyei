package com.hajdu.sp.competition.update.validation;

import com.hajdu.sp.competition.update.command.competition.AddClub;
import com.hajdu.sp.competition.update.domain.CompetitionAggregate;
import com.hajdu.sp.competition.update.exceptions.ExceptionValue;
import com.hajdu.sp.competition.update.exceptions.InvariantException;
import com.hajdu.sp.competition.update.exceptions.NotFoundException;
import com.hajdu.sp.competition.update.service.ClubService;
import com.hajdu.sp.competition.update.util.Invariant;
import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class AddClubValidator implements Invariant<CompetitionAggregate, AddClub> {

    private final ClubService clubService;

    public void check(CompetitionAggregate competitionAggregate, AddClub addClub) throws ExecutionException, InterruptedException {
        Stage stage = getStage(competitionAggregate, addClub);

        checkExistingClub(addClub, stage);

        checkNumberOfClubs(addClub.getClubId(), stage);
    }

    private Stage getStage(CompetitionAggregate competitionAggregate, AddClub addClub) {
        StageId stageId = addClub.getStageId();

        return competitionAggregate.getSeasons()
                .findStageById(addClub.getStageId())
                .orElseThrow(() -> new NotFoundException(stageId.toString()));
    }

    private void checkExistingClub(AddClub addClub, Stage stage) throws ExecutionException, InterruptedException {
        clubService.find(addClub.getClubId().getId())
                .orElseThrow(() -> new NotFoundException(addClub.getClubId().toString()));

        if(stage.getClubIds().contains(addClub.getClubId())) {
            throw new InvariantException("The club has been already added", ExceptionValue.builder().value(addClub.getClubId().getId()).build());
        }
    }

    private void checkNumberOfClubs(ClubId newClubId, Stage stage) {
        int numberOfClubs = stage.getStageRuleSet().getNumberOfTeams();
        int newNumberOfClubs = stage.getClubIds().size() + 1;
        if(numberOfClubs < newNumberOfClubs) {
            throw new InvariantException("The number of clubs has reached the maximum", ExceptionValue.builder().value(newClubId).build());
        }
    }
}
