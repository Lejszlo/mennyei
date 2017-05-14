package com.sp.organizer.backend.competition.domain;

import com.sp.core.backend.exception.MaxClubLimitIsReached;
import com.sp.organizer.backend.competition.command.AddCompetitionCommand;
import com.sp.organizer.backend.competition.command.CompetitionCommand;
import com.sp.organizer.backend.competition.command.RegisterClubCommand;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import com.sp.organizer.backend.competition.domain.season.Stage;
import com.sp.organizer.backend.competition.events.ClubRegistered;
import com.sp.organizer.backend.competition.events.CompetitionAdded;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;

/**
 * Created by lejsz on 2017. 05. 14..
 */
public class CompetitionAggregate extends ReflectiveMutableCommandProcessingAggregate<CompetitionAggregate, CompetitionCommand> {

    private CompetitionInfo competitionInfo;

    private CompetitionRuleSet competitionRules;

    private Set<Stage> stages = new HashSet<>();

    private Set<String> clubIds = new HashSet<>();

    public List<Event> process(AddCompetitionCommand addCompetitionCommand) {
        return Arrays.asList(CompetitionAdded.builder().competitionInfo(addCompetitionCommand.getCompetitionInfo())
                .competitionRuleSet(addCompetitionCommand.getCompetitionRules()).stages(addCompetitionCommand.getStages()).build());
    }

    public List<Event> process(RegisterClubCommand registerClubCommand) throws MaxClubLimitIsReached {
        if (competitionRules.getNumberOfTeams() == clubIds.size()) {
            throw new MaxClubLimitIsReached();
        }
        return Arrays.asList(ClubRegistered.builder().clubIds(registerClubCommand.getClubIds()).build());
    }


    public void apply(ClubRegistered clubRegistered) {
        clubIds.addAll(clubRegistered.getClubIds());
    }

    public void apply(CompetitionAdded competationAdded) {
        competitionInfo = competationAdded.getCompetitionInfo();
        competitionRules = competationAdded.getCompetitionRuleSet();
        stages.addAll(competationAdded.getStages());
    }

    private Optional<Stage> findStageByName(String stageName) {
        return stages.stream().filter(s -> s.getName().equals(stageName)).findFirst();
    }


}
