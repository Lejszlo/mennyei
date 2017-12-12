package com.sp.organizer.command.aggregator.competition.domain;

import com.sp.core.backend.exception.MaxClubLimitIsReached;
import command.competition.AddStageCommand;
import command.competition.SaveCompetitionCommand;
import command.competition.CompetitionCommand;
import value.competition.CompetitionInfo;
import value.competition.season.Stage;
import event.competition.CompetitionAdded;
import event.competition.StageAdded;
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

    public List<Event> process(AddStageCommand addStageCommand) throws MaxClubLimitIsReached {
        if (addStageCommand.getStage().getStageRuleSet().getNumberOfTeams() < addStageCommand.getStage().getClubIds().size()) {
            throw new MaxClubLimitIsReached();
        }
        return Collections.singletonList(StageAdded.builder()
                .stage(addStageCommand.getStage())
                .build());
    }

    public void apply(CompetitionAdded competationAdded) {
        competitionInfo = competationAdded.getCompetitionInfo();
    }

    public void apply(StageAdded stageAdded) {
        stages.add(stageAdded.getStage());
    }


}
