package com.sp.organizer.command.aggregator.organizer.domain;

import com.sp.organizer.api.command.organizer.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.command.organizer.CreateOrganizerCommand;
import com.sp.organizer.api.command.organizer.OrganizerCommand;
import com.sp.organizer.api.event.organizer.CompetitionAdded;
import com.sp.organizer.api.event.organizer.OrganizerCreated;
import com.sp.organizer.api.value.competition.CompetitionId;
import com.sp.organizer.api.value.organizer.OrganizerInfo;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.*;

public class OrganizerAggregate extends ReflectiveMutableCommandProcessingAggregate<OrganizerAggregate, OrganizerCommand> {

    private OrganizerInfo organizerInfo;

    private Set<CompetitionId> competitions = new HashSet<>();

    public List<Event> process(CreateOrganizerCommand createOrganizerCommand) {
        return Collections.singletonList(
                OrganizerCreated.builder()
                        .organizerInfo(createOrganizerCommand.getOrganizerInfo())
                        .build());
    }

    public List<Event> process(AddCompetitionOrganizerCommand addCompetitionOrganizerCommand) {
        return Collections.singletonList(
                CompetitionAdded.builder()
                        .competitionId(addCompetitionOrganizerCommand.getCompetitionId())
                        .build());
    }

    public void apply(OrganizerCreated organizerCreated) {
        organizerInfo = organizerCreated.getOrganizerInfo();
    }

    public void apply(CompetitionAdded competitionAdded) {
        competitions.add(competitionAdded.getCompetitionId());
    }

}
