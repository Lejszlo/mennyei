package com.hajdu.sp.competition.update.value.competition.ids;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class SeasonId {

    @NonNull
    private UUID seasonUuid;

    private SeasonId(UUID seasonUuid) {
        this.seasonUuid = seasonUuid;
    }

    public static SeasonId seasonId(String id) {
        return new SeasonId(UUID.fromString(id));
    }

    public static SeasonId seasonId() {
        return new SeasonId(UUID.randomUUID());
    }
}
