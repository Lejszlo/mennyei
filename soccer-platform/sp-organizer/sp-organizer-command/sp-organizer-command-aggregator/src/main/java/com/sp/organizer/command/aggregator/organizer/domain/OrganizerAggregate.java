package com.sp.organizer.command.aggregator.organizer.domain;

import com.sp.organizer.api.command.AddCompetitionOrganizerCommand;
import com.sp.organizer.api.command.CreateOrganizerCommand;
import com.sp.organizer.api.command.OrganizerCommand;
import com.sp.organizer.api.event.CompetitionAdded;
import com.sp.organizer.api.event.OrganizerCreated;
import com.sp.organizer.api.value.OrganizerInfo;
import io.eventuate.Event;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import sp.competition.api.value.CompetitionId;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
