package com.hajdu.sp.competition.update.value.competition.ids;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class SeasonId {
    @NonNull
    private CompetitionId competitionId;
    @NonNull
    private UUID seasonUuid;

    private SeasonId(CompetitionId competitionId, UUID seasonUuid) {
        this.competitionId = competitionId;
        this.seasonUuid = seasonUuid;
    }

    public static SeasonId seasonId(CompetitionId competitionId, UUID seasonId) {
        return new SeasonId(competitionId, seasonId);
    }

    public CompetitionId getCompetitionId() {
        return competitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeasonId seasonId1 = (SeasonId) o;
        return competitionId.equals(seasonId1.competitionId) &&
                seasonUuid.equals(seasonId1.seasonUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionId, seasonUuid);
    }
}
