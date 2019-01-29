package com.hajdu.sp.match.query.service.generator;

import com.google.common.collect.Lists;
import com.hajdu.sp.club.lib.value.ClubId;
import com.hajdu.sp.match.lib.value.MatchInfo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

import static com.hajdu.sp.club.lib.value.AwayClubId.awayClubId;
import static com.hajdu.sp.club.lib.value.HomeClubId.homeClubId;


@Service
public class RoundRubinTournamentGenerator {

    public Set<MatchInfo> generate(List<ClubId> clubIds) {
        ClubId fixed = clubIds.get(0);
        LinkedList<ClubId> clock = Lists.newLinkedList(clubIds);
        return generate(clock, fixed);
    }

    private Set<MatchInfo> generate(LinkedList<ClubId> clock, ClubId fixed) {
        Set<MatchInfo> matches = new HashSet<>();
        IntStream.range(0, clock.size() - 1).forEach(value -> {
            LinkedList<ClubId> homeSide = Lists.newLinkedList(clock.subList(1, clock.size() / 2));
            LinkedList<ClubId> awaySide = Lists.newLinkedList(Lists.reverse(clock.subList(clock.size() / 2, clock.size())));

            matches.addAll(pairRound(fixed, homeSide, awaySide));

            turnClock(clock);
        });
        return matches;
    }

    private List<MatchInfo> pairRound(ClubId fixed, LinkedList<ClubId> homeSide, LinkedList<ClubId> awaySide) {
        List<MatchInfo> matches = new ArrayList<>();
        matches.add(createMatchInfo(fixed.getId(), Optional.ofNullable(awaySide.poll())
                .orElseThrow(RuntimeException::new)
                .getId()));
        IntStream.range(0, awaySide.size())
                .forEach(index -> matches.add(createMatchInfo(homeSide.get(index).getId(), awaySide.get(index).getId())));
        return matches;
    }

    private MatchInfo createMatchInfo(String homeClubId, String awayClubId) {
        return MatchInfo.builder()
                .homeClubId(homeClubId(homeClubId))
                .awayClubId(awayClubId(awayClubId))
                .build();
    }

    private void turnClock(LinkedList<ClubId> list) {
        ClubId fixed = list.pollFirst();
        ClubId last = list.pollLast();
        list.offerFirst(last);
        list.offerFirst(fixed);
    }

}
