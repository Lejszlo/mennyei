package com.sp.organizer.command.aggregator.competition.domain;

import com.sp.core.backend.web.resource.IdResource;
import com.sp.organizer.api.command.competition.AddClubsToStageCommand;
import com.sp.organizer.api.command.competition.AddStageCommand;
import com.sp.organizer.api.command.competition.SaveCompetitionCommand;
import com.sp.organizer.api.command.competition.CompetitionCommand;
import com.sp.organizer.api.event.competition.ClubsAddedToStage;
import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.api.event.competition.CompetitionAdded;
import com.sp.organizer.api.event.competition.StageAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;
import java.util.stream.Collectors;

public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {

    private CompetitionInfo competitionInfo;

    private List<Stage> stages = new ArrayList<>();

    public List<Event> process(SaveCompetitionCommand addCompetitionCommand) {
        return Collections.singletonList(CompetitionAdded.builder()
                .competitionInfo(addCompetitionCommand.getCompetitionInfo()).build());
    }

    public List<Event> process(AddStageCommand addStageCommand) {
        return Collections.singletonList(StageAdded.builder()
                .stage(addStageCommand.getStage())
                .build());
    }

    public List<Event> process(AddClubsToStageCommand addClubsToStageCommand) {
        return Collections.singletonList(ClubsAddedToStage.builder()
                .stageId(addClubsToStageCommand.getStageId())
                .clubIds(addClubsToStageCommand.getClubIds())
                .build());
    }

    public void apply(AddClubsToStageCommand addClubsToStageCommand) {
        stages.stream()
                .filter(stage -> stage.getId().equals(addClubsToStageCommand.getStageId()))
                .findFirst()
                .ifPresent(stage -> stage.getClubIds().addAll(addClubsToStageCommand.getClubIds()
                        .stream()
                        .map(UUID::toString)
                        .collect(Collectors.toList()))
                );
    }

    public void apply(CompetitionAdded competitionAdded) {
        competitionInfo = competitionAdded.getCompetitionInfo();
    }

    public void apply(StageAdded stageAdded) {
        stages.add(stageAdded.getStage());
    }


}
