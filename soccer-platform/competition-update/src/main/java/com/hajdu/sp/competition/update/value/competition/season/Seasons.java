package com.hajdu.sp.competition.update.value.competition.season;

import com.hajdu.sp.competition.update.value.club.ClubId;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.ids.TurnId;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;
import com.hajdu.sp.competition.update.value.match.MatchId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Value
public class Seasons extends ArrayList<Season> {

    public void addStages(SeasonId seasonId, Stage... stage) {
        this.stream()
                .filter(season -> season.getId().equals(seasonId))
                .findFirst()
                .ifPresent(season -> season.getStages().addAll(List.of(stage)));
    }

    public void addClubs(StageId stageId, ClubId... clubIds) {
        this.stream()
                .flatMap(season -> season.getStages().stream())
                .filter(stage -> stage.getId().equals(stageId))
                .findFirst()
                .ifPresent(season -> season.getClubIds().addAll(List.of(clubIds)));
    }

    public void addTurns(StageId stageId, Turn... turns) {
        this.stream()
                .flatMap(season -> season.getStages().stream())
                .filter(stage -> stage.getId().equals(stageId))
                .findFirst()
                .ifPresent(stage -> stage.getTurns().addAll(List.of(turns)));
    }

    public void addMatches(TurnId turnId, MatchId... matchIds) {
        this.stream()
                .flatMap(season -> season.getStages().stream())
                .flatMap(stage -> stage.getTurns().stream())
                .filter(turn -> turn.getTurnId().equals(turnId))
                .findFirst()
                .ifPresent(turn -> turn.getMatches().addAll(List.of(matchIds)));
    }

    public Optional<Season> findLatestSeason(LocalDateTime localDateTime) {
        return this.stream()
                .filter(season -> season.getInterval().getContainsPredicate(localDateTime))
                .findFirst();
    }

    public Optional<Stage> findStageById(StageId stageId) {
        return this.stream()
                .flatMap(season -> season.getStages().stream())
                .filter(stage -> stage.getId().equals(stageId))
                .findFirst();
    }

}
