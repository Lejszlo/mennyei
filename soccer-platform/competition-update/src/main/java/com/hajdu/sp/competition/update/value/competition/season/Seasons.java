package com.hajdu.sp.competition.update.value.competition.season;

import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Value
public class Seasons extends ArrayList<Season> {

    public Optional<Season> findLatestSeason(LocalDateTime localDateTime) {
        return this.stream()
                .filter(season -> season.getInterval().getContainsPredicate(localDateTime))
                .findFirst();
    }

    public Optional<Stage> findStageById(StageId stageId) {
        return this.stream()
                .flatMap(season -> season.getStages().stream())
                .filter(stage -> stage.getId().equals(stageId))
                .findFirst();
    }

}
