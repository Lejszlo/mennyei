package com.hajdu.sp.competition.lib.value;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import static java.util.Comparator.comparing;

@Value
@AllArgsConstructor
public class TurnId implements Comparable<TurnId> {

    @NonNull
    Integer index;

    @NonNull
    StageId stageId;

    public static TurnId turnId(int index, StageId stageId) {
        return new TurnId(index, stageId);
    }

    @Override
    public int compareTo(TurnId that) {
        return comparing(TurnId::getIndex).compare(this, that);
    }

    public CompetitionId getCompetitionId() {
        return stageId.getCompetitionId();
    }
}
