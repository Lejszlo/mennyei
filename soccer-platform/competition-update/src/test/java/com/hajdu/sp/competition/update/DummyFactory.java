package com.hajdu.sp.competition.update;

import com.hajdu.sp.competition.update.command.competition.AddSeason;
import com.hajdu.sp.competition.update.command.competition.AddStage;
import com.hajdu.sp.competition.update.command.competition.AddTurn;
import com.hajdu.sp.competition.update.command.competition.CreateCompetition;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.organizer.Organizer;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import com.hajdu.sp.competition.update.value.competition.turn.Turn;

import java.util.List;
import java.util.UUID;

import static com.hajdu.sp.competition.update.util.Interval.from;
import static com.hajdu.sp.competition.update.value.competition.SeasonInfo.builder;
import static com.hajdu.sp.competition.update.value.competition.ids.SeasonId.seasonId;
import static com.hajdu.sp.competition.update.value.competition.ids.StageId.stageId;
import static com.hajdu.sp.competition.update.value.competition.ids.TurnId.turnId;
import static com.hajdu.sp.competition.update.value.competition.rule.SortingRule.*;
import static com.hajdu.sp.competition.update.value.match.MatchId.matchId;
import static java.time.LocalDateTime.now;

public class DummyFactory {

    public static AddTurn dummyTurn(StageId stageId) {
        return AddTurn.builder()
                .stageId(stageId)
                .turn(Turn.builder()
                        .interval(from(now(), now().plusWeeks(1)))
                        .match(matchId(UUID.randomUUID().toString()))
                        .turnId(turnId(0))
                        .build())
                .build();
    }

    public static AddStage dummyStage(SeasonId seasonId) {
        return AddStage.builder()
                .name("First Season")
                .interval(from(now(), now().plusMonths(10)))
                .stageId(stageId())
                .seasonId(seasonId)
                .stageRuleSet(
                        StageRuleSet.builder()
                                .numberOfTeams(10)
                                .sortingRules(List.of(GAMES_WON, GOAL_DIFFERENCE, GOAL_SCORED))
                                .build()
                )
                .build();
    }

    public static AddSeason dummySeason() {
        return AddSeason.builder()
                .seasonId(seasonId())
                .seasonInfo(builder()
                        .description("Description")
                        .name("Competition")
                        .build())
                .interval(from(now(), now().plusMonths(6)))
                .build();
    }

    public static CreateCompetition dummyCompetition() {
        return CreateCompetition.builder()
                .organizer(
                        Organizer.builder()
                                .email("email@email.com")
                                .name("Test Organizer")
                                .phoneNumber("123456789")
                                .build()
                )
                .build();
    }

}
