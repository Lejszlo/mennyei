package com.hajdu.sp.competition.update.command.competition;

import com.hajdu.sp.competition.update.util.Interval;
import com.hajdu.sp.competition.update.value.competition.ids.StageId;
import com.hajdu.sp.competition.update.value.competition.rule.StageRuleSet;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
public class AddStage extends CompetitionCommand {

    @NonNull
    private StageId stageId;

    @NonNull
    private String name;

    @NonNull
    private StageRuleSet stageRuleSet;

    @NonNull
    private Interval interval;
}


