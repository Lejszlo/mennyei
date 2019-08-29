package com.hajdu.sp.competition.update.value.competition.ids;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@ToString
public class StageId {

    @NonNull
    private UUID stageUuid;

    private StageId() {
        this.stageUuid = UUID.randomUUID();
    }

    public static StageId stageId() {
        return new StageId();
    }

}
