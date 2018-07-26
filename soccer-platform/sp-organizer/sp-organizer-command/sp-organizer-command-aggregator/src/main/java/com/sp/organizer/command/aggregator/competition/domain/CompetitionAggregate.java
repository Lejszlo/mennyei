package com.sp.organizer.command.aggregator.competition.domain;

import com.sp.organizer.api.command.competition.CompetitionCommand;
import com.sp.organizer.api.command.competition.*;
import com.sp.organizer.api.event.competition.ClubsAdded;
import com.sp.organizer.api.event.competition.TurnsAdded;
import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.api.event.competition.CompetitionCreated;
import com.sp.organizer.api.event.competition.StageAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;

public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {

    private CompetitionInfo competitionInfo;

    private List<Stage> stages = new ArrayList<>();

    public List<Event> process(CreateCompetitionCommand addCompetitionCommand) {
        return Collections.singletonList(CompetitionCreated.builder()
                .competitionInfo(addCompetitionCommand.getCompetitionInfo()).build());
    }

    public List<Event> process(AddStageCompetitionCommand addStageCompetitionCommand) {
        return Collections.singletonList(StageAdded.builder()
                .stage(Stage.builder()
                        .name(addStageCompetitionCommand.getName())
                        .interval(addStageCompetitionCommand.getInterval())
                        .stageRuleSet(addStageCompetitionCommand.getStageRuleSet())
                        .build())
                .build());
    }

    public List<Event> process(AddClubsCompetitionCommand addClubsCompetitionCommand) {
        return Collections.singletonList(ClubsAdded.builder()
                .stageId(addClubsCompetitionCommand.getStageId().getStageId())
                .clubIds(addClubsCompetitionCommand.getClubIds())
                .build());
    }

    public List<Event> process(AddTurnsCompetitionCommand addClubsToStageCommand) {
        return Collections.singletonList(TurnsAdded.builder()
                .stageId(addClubsToStageCommand.getStageId().getStageId())
                .turns(addClubsToStageCommand.getTurnIds())
                .build());
    }

    public void apply(ClubsAdded clubsAdded) {
        stages.stream()
                .filter(stage -> stage.getId().equals(clubsAdded.getStageId()))
                .findFirst()
                .ifPresent(stage -> stage.getClubIds().addAll(new ArrayList<String>(clubsAdded.getClubIds()))
                );
    }

    public void apply(TurnsAdded clubsAddedToStage) {
        stages.stream()
                .filter(stage -> stage.getId().equals(clubsAddedToStage.getStageId()))
                .findFirst()
                .ifPresent(stage -> stage.getTurns().addAll(clubsAddedToStage.getTurns()));
    }

    public void apply(CompetitionCreated competitionCreated) {
        competitionInfo = competitionCreated.getCompetitionInfo();
    }

    public void apply(StageAdded stageAdded) {
        stages.add(stageAdded.getStage());
    }


}
