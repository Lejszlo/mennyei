package com.sp.organizer.command.aggregator.competition.domain;

import com.sp.core.backend.exception.MaxClubLimitIsReached;
import com.sp.organizer.api.competition.AddStageCommand;
import com.sp.organizer.api.competition.SaveCompetitionCommand;
import com.sp.organizer.api.competition.CompetitionCommand;
import com.sp.organizer.api.value.competition.CompetitionInfo;
import com.sp.organizer.api.value.competition.season.Stage;
import com.sp.organizer.api.event.competition.CompetitionAdded;
import com.sp.organizer.api.event.competition.StageAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;

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

    public void apply(CompetitionAdded competitionAdded) {
        competitionInfo = competitionAdded.getCompetitionInfo();
    }

    public void apply(StageAdded stageAdded) {
        stages.add(stageAdded.getStage());
    }


}
