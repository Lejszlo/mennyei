package com.sp.organizer.api.value.competition;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StageId {
    private CompetitionId competitionId;
    private String id;

    private StageId(CompetitionId value, String id) {
        this.competitionId = value;
        this.id = id;
    }

    public static StageId stageId(CompetitionId competitionId, String id) {
        return new StageId(competitionId, id);
    }
}
