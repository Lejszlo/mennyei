package com.sp.match.query.viewer.service;

import com.sp.club.api.value.ClubId;
import com.sp.match.api.value.MatchInfo;
import com.sp.match.query.viewer.service.generator.RoundRubinTournamentGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class RoundRubinTournamentGeneratorTest {

    private RoundRubinTournamentGenerator matchesGenerator = new RoundRubinTournamentGenerator();

    @Test
    void generateRoundRobinTournament() {
        // GIVEN
        List<ClubId> minClubSize = IntStream.range(1, 3)
                .mapToObj(String::valueOf)
                .map(ClubId::clubId)
                .collect(Collectors.toList());

        List<ClubId> middleClubSize = IntStream.range(1, 11)
                .mapToObj(String::valueOf)
                .map(ClubId::clubId)
                .collect(Collectors.toList());

        List<ClubId> highClubSize = IntStream.range(1, 25)
                .mapToObj(String::valueOf)
                .map(ClubId::clubId)
                .collect(Collectors.toList());

        // WHEN
        Set<MatchInfo> minMatchInfos = matchesGenerator.generate(minClubSize);
        Set<MatchInfo> middleMatchInfos = matchesGenerator.generate(middleClubSize);
        Set<MatchInfo> highMatchInfos = matchesGenerator.generate(highClubSize);

        // THEN
        assertThat(minMatchInfos, notNullValue());
        assertThat(minMatchInfos, hasSize((minClubSize.size() / 2) * minClubSize.size() - 1));

        assertThat(middleMatchInfos, notNullValue());
        assertThat(middleMatchInfos, hasSize((middleClubSize.size() / 2) * (middleClubSize.size() - 1)));

        assertThat(highMatchInfos, notNullValue());
        assertThat(highMatchInfos, hasSize((highClubSize.size() / 2) * (highClubSize.size() - 1)));
    }
}