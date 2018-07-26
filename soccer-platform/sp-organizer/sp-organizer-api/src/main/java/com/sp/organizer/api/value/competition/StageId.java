package com.sp.organizer.api.value.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.sp.organizer.api.value.competition.CompetitionId.competitionId;

@NoArgsConstructor
@Getter
public class StageId {
    private CompetitionId competitionId;
    private UUID stageId;

    private StageId(String competitionId, UUID stageId) {
        this.competitionId = competitionId(competitionId);
        this.stageId = stageId;
    }

    public static StageId stageId(String competitionId, UUID stageId) {
        return new StageId(competitionId, stageId);
    }
}
