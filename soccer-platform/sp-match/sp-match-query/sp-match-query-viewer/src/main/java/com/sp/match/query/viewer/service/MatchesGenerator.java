package com.sp.match.query.viewer.service;

import com.google.common.collect.Lists;
import com.sp.club.api.value.ClubId;
import com.sp.match.api.value.MatchInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.sp.club.api.value.AwayClubId.*;
import static com.sp.club.api.value.HomeClubId.*;

@Service
public class MatchesGenerator {

    public List<MatchInfo> generateRoundRobinTournament(List<ClubId> clubIds) {
        ClubId fixed = clubIds.get(0);

        LinkedList<ClubId> clock = Lists.newLinkedList(clubIds);
        return generate(clock, fixed);
    }

    private List<MatchInfo> generate(LinkedList<ClubId> clock, ClubId fixed) {
        List<MatchInfo> matches = new ArrayList<>();
        IntStream.range(0, clock.size() - 1).forEach(value -> {
            LinkedList<ClubId> homeSide = Lists.newLinkedList(clock.subList(1, clock.size() / 2));
            LinkedList<ClubId> awaySide = Lists.newLinkedList(clock.subList(clock.size() / 2, clock.size()));

            matches.addAll(pairRound(fixed, homeSide, awaySide));

            turnClock(Lists.newLinkedList(clock.subList(1, clock.size())));
        });
        return matches;
    }

    private List<MatchInfo> pairRound(ClubId fixed, LinkedList<ClubId> homeSide, LinkedList<ClubId> awaySide) {
        List<MatchInfo> matches = new ArrayList<>();
        createMatchInfo(fixed.getId(), awaySide.get(0).getId());
        IntStream.range(0, homeSide.size())
                .forEach(index -> matches.add(createMatchInfo(homeSide.get(index).getId(), awaySide.get(index + 1).getId())));
        return matches;
    }

    private MatchInfo createMatchInfo(String homeClubId, String awayClubId) {
        return MatchInfo.builder()
                .homeClubId(homeClubId(homeClubId))
                .awayClubId(awayClubId(awayClubId))
                .build();
    }

    private LinkedList<ClubId> turnClock(LinkedList<ClubId> list) {
        ClubId last = list.pollLast();
        list.offerFirst(last);
        return list;
    }

}
