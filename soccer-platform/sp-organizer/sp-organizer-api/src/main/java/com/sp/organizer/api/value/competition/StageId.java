package com.sp.organizer.api.value.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;
import java.util.UUID;

import static com.sp.organizer.api.value.competition.CompetitionId.competitionId;
import static com.sp.organizer.api.value.competition.SeasonId.seasonId;

@NoArgsConstructor
@Getter
public class StageId {
    @NonNull
    private SeasonId seasonId;
    @NonNull
    private UUID stageUuid;

    private StageId(SeasonId seasonId, UUID stageUuid) {
        this.seasonId = seasonId;
        this.stageUuid = stageUuid;
    }

    public static StageId stageId(SeasonId seasonId, UUID stageId) {
        return new StageId(seasonId, stageId);
    }

    public static StageId stageId(String competitionId, String seasonId, String stageId) {
        return stageId(seasonId(competitionId(competitionId), UUID.fromString(seasonId)), UUID.fromString(stageId));
    }

    public CompetitionId getCompetitionId() {
        return seasonId.getCompetitionId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageId stageId1 = (StageId) o;
        return seasonId.equals(stageId1.seasonId) &&
                stageUuid.equals(stageId1.stageUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonId, stageUuid);
    }
}
