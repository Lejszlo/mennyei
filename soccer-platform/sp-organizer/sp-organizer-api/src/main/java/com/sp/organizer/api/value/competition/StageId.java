package com.sp.organizer.api.value.competition;

public class StageId {
    private CompetitionId competitionId;
    private int index;

    private StageId(CompetitionId value, int index) {
        this.competitionId = value;
        this.index = index;
    }

    public static StageId stageId(CompetitionId competitionId, int index) {
        return new StageId(competitionId, index);
    }

    public CompetitionId getCompetitionId() {
        return competitionId;
    }

    public int getIndex() {
        return index;
    }
}
