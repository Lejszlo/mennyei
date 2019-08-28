package com.hajdu.sp.competition.update.value.competition.stage;

import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Value
public class Stages extends ArrayList<Stage> {

    public Optional<Stage> getLatestStage(LocalDateTime localDateTime) {
        return this.stream()
                .filter(stage -> stage.getInterval().getContainsPredicate(localDateTime))
                .findFirst();
    }

    public Optional<Stage> getByStageId(StageId stageId) {
        return this.stream()
                .filter(stage -> stage.getId().equals(stageId))
                .findFirst();
    }

}
