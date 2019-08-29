package com.hajdu.sp.competition.update.domain;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.event.club.ClubAdded;
import com.hajdu.sp.competition.update.event.competition.*;
import com.hajdu.sp.competition.update.validation.CompetitionAggregateValidator;
import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import com.hajdu.sp.competition.update.value.competition.season.Season;
import com.hajdu.sp.competition.update.value.competition.season.Seasons;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import com.hajdu.sp.competition.update.value.competition.stage.Stages;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {
    private Organizer organizer;
    private Seasons seasons;

    public List<Event> process(CreateCompetition createCompetition) {
        return Collections.singletonList(CompetitionCreated.builder()
                .organizer(createCompetition.getOrganizer())
                .build());
    }

    public List<Event> process(AddSeason addSeason) {
        return EventUtil.events(SeasonAdded.builder()
                .season(Season.builder()
                        .id(addSeason.getSeasonId())
                        .stages(new Stages())
                        .interval(addSeason.getInterval())
                        .seasonInfo(addSeason.getSeasonInfo())
                        .build())
                .build());
    }

    public List<Event> process(AddStage addStage) {
        return EventUtil.events(StageAdded.builder()
                .stage(Stage.builder()
                        .id(addStage.getStageId())
                        .name(addStage.getName())
                        .interval(addStage.getInterval())
                        .stageRuleSet(addStage.getStageRuleSet())
                        .build())
                .seasonId(addStage.getSeasonId())
                .build());
    }

    public List<Event> process(AddTurn addTurn) {
        return EventUtil.events(TurnAdded.builder()
                .stageId(addTurn.getStageId())
                .turn(addTurn.getTurn())
                .build());
    }

    public List<Event> process(AddMatch addMatch) {
        return EventUtil.events(MatchAdded.builder()
                .turnId(addMatch.getTurnId())
                .matchId(addMatch.getMatchId())
                .build());
    }


    public List<Event> process(AddClub addClub) {
        CompetitionAggregateValidator.check(this, addClub);
        return EventUtil.events(ClubAdded.builder()
                .stageId(addClub.getStageId())
                .clubId(addClub.getClubId())
                .build());
    }

    //----- EVENTS -----
    public void apply(CompetitionCreated competitionCreated) {
        this.organizer = competitionCreated.getOrganizer();
        this.seasons = new Seasons();
    }

    public void apply(SeasonAdded seasonAdded) {
        seasons.add(seasonAdded.getSeason());
    }

    public void apply(StageAdded stageAdded) {
        seasons.addStages(stageAdded.getSeasonId(), stageAdded.getStage());
    }

    public void apply(ClubAdded clubAdded) {
        seasons.addClubs(clubAdded.getStageId(), clubAdded.getClubId());
    }

    public void apply(TurnAdded turnAdded) {
        seasons.addTurns(turnAdded.getStageId());
    }

    public void apply(MatchAdded matchAdded) {
        seasons.addMatches(matchAdded.getTurnId(), matchAdded.getMatchId());
    }

}
