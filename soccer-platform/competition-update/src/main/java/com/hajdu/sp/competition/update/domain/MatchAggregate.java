package com.hajdu.sp.competition.update.domain;

import com.hajdu.sp.competition.update.command.match.CreateMatch;
import com.hajdu.sp.competition.update.command.match.MatchCommand;
import com.hajdu.sp.competition.update.event.club.ClubCreated;
import com.hajdu.sp.competition.update.event.match.MatchCreated;
import com.hajdu.sp.competition.update.value.match.MatchInfo;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.List;

public class MatchAggregate extends ReflectiveMutableCommandProcessingAggregate<MatchAggregate, MatchCommand> {

    MatchInfo matchInfo;

    public List<Event> process(CreateMatch createMatch) {
        return EventUtil.events(new MatchCreated(createMatch.getMatchInfo()));
    }

    public void apply(MatchCreated matchCreated) {
        matchInfo = matchCreated.getMatchInfo();
    }

}
