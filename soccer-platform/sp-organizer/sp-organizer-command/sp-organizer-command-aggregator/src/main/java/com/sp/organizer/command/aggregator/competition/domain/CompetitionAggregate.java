package com.sp.organizer.command.aggregator.competition.domain;

import com.sp.organizer.api.command.competition.*;
import com.sp.organizer.api.event.competition.*;
import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.api.value.competition.season.Season;
import com.sp.organizer.api.value.competition.season.Stage;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.sp.organizer.command.aggregator.competition.validation.CompetitionAggregateValidator.checkClubsInvariant;

public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {

    private CompetitionInfo competitionInfo;

    private List<Season> seasons = new ArrayList<>();

    public List<Event> process(CreateCompetition addCompetitionCommand) {
        return Collections.singletonList(CompetitionCreated.builder()
                .competitionInfo(addCompetitionCommand.getCompetitionInfo()).build());
    }

    public List<Event> process(AddSeason addSeason) {
        //        TODO Season invariant check
        return Collections.singletonList(SeasonAdded.builder()
                .season(Season.builder()
                        .id(addSeason.getSeasonId())
                        .name(addSeason.getName())
                        .interval(addSeason.getInterval())
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

    public List<Event> process(AddClubs addClubs) {
        checkClubsInvariant(this, addClubs);
        return Collections.singletonList(ClubsAdded.builder()
                .stageId(addClubs.getStageId().getStageUuid())
                .seasonId(addClubs.getStageId().getSeasonId().getSeasonUuid())
                .clubIds(addClubs.getClubIds())
                .build());
    }

    public List<Event> process(AddTurns addClubsToStageCommand) {
        return Collections.singletonList(TurnsAdded.builder()
                .stageId(addClubsToStageCommand.getStageId().getStageUuid())
                .turns(addClubsToStageCommand.getTurnIds())
                .build());
    }

    //----- EVENTS -----
    public void apply(CompetitionCreated competitionCreated) {
        competitionInfo = competitionCreated.getCompetitionInfo();
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
                .ifPresent(stage -> stage.getClubIds().addAll(new ArrayList<String>(clubsAdded.getClubIds()))
         );
    }

    public List<Season> getSeasons() {
        return Collections.unmodifiableList(seasons);
    }

}
