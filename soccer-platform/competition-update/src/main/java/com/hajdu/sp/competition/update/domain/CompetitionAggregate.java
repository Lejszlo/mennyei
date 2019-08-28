package com.hajdu.sp.competition.update.domain;

import com.hajdu.sp.competition.update.command.competition.*;
import com.hajdu.sp.competition.update.event.club.ClubsAdded;
import com.hajdu.sp.competition.update.event.competition.CompetitionCreated;
import com.hajdu.sp.competition.update.event.competition.SeasonAdded;
import com.hajdu.sp.competition.update.event.competition.StageAdded;
import com.hajdu.sp.competition.update.event.competition.TurnsAdded;
import com.hajdu.sp.competition.update.util.InvariantValidator;
import com.hajdu.sp.competition.update.validation.CompetitionAggregateValidator;
import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import com.hajdu.sp.competition.update.value.competition.season.Seasons;
import com.hajdu.sp.competition.update.value.competition.season.Season;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Collections;
import java.util.List;

public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {
    private Organizer organizer;
    private Seasons seasons;

    public List<Event> process(CreateCompetition command) {
        return Collections.singletonList(CompetitionCreated.builder()
                .organizer(command.getOrganizer())
                .build());
    }

    public List<Event> process(AddSeason addSeason) {
        //        TODO Season invariant check
        return Collections.singletonList(SeasonAdded.builder()
                .season(Season.builder()
                        .id(addSeason.getSeasonId())
                        .competitionInfo(addSeason.getCompetitionInfo())
                        .build())
                .build());
    }

    public List<Event> process(AddStage addStage) {
        //        TODO Stage invariant check
        return Collections.singletonList(StageAdded.builder()
                .stage(Stage.builder()
                        .id(addStage.getStageId())
                        .name(addStage.getName())
                        .interval(addStage.getInterval())
                        .stageRuleSet(addStage.getStageRuleSet())
                        .build())
                .build());
    }

    @InvariantValidator(clazz = CompetitionAggregateValidator.class)
    public List<Event> process(AddClub addClub) {
        return Collections.singletonList(ClubsAdded.builder()
                .stageId(addClub.getStageId().getStageUuid())
                .seasonId(addClub.getStageId().getSeasonId().getSeasonUuid())
                .club(addClub.getClub())
                .build());
    }

    public List<Event> process(AddTurn addClubsToStageCommand) {
        return Collections.singletonList(TurnsAdded.builder()
                .stageId(addClubsToStageCommand.getStageId().getStageUuid())
                .turn(addClubsToStageCommand.getTurn())
                .build());
    }

    //----- EVENTS -----
    public void apply(CompetitionCreated competitionCreated) {
        this.organizer = competitionCreated.getOrganizer();
    }

    public void apply(SeasonAdded seasonAdded) {
        seasons.add(seasonAdded.getSeason());
    }

    public void apply(StageAdded stageAdded) {
        seasons.stream()
                .filter(season -> season.getId().getSeasonUuid().equals(stageAdded.getStage().getId().getSeasonId().getSeasonUuid()))
                .findFirst()
                .ifPresent(season -> season.getStages().add(stageAdded.getStage()));
    }

    public void apply(ClubsAdded clubsAdded) {
        seasons.stream()
                .flatMap(s -> s.getStages().stream())
                .filter(stage -> stage.getId().getStageUuid().equals(clubsAdded.getStageId()))
                .findFirst()
                .ifPresent(stage -> stage.getClubIds().add(clubsAdded.getClub())
                );
    }

    public Seasons getSeasons() {
        return seasons;
    }

}
