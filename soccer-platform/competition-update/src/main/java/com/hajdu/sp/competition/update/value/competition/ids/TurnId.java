package com.hajdu.sp.competition.update.value.competition.ids;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import static java.util.Comparator.comparing;

@Value
@AllArgsConstructor
public class TurnId implements Comparable<TurnId> {

    @NonNull
    Integer index;

    public static TurnId turnId(int index) {
        return new TurnId(index);
    }

    @Override
    public int compareTo(TurnId that) {
        return comparing(TurnId::getIndex).compare(this, that);
    }
}
