package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.value.competition.CompetitionInfo;
import com.hajdu.sp.competition.update.value.competition.ids.SeasonId;
import com.hajdu.sp.competition.update.value.competition.stage.Stage;
import lombok.*;

import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper = true)
public class AddSeason extends CompetitionCommand {

    @NonNull
    CompetitionInfo competitionInfo;

    @NonNull
    SeasonId seasonId;

    @Singular
    List<Stage> stages;

}
