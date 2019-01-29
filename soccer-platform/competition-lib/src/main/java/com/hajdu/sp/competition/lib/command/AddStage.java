package com.hajdu.sp.competition.lib.command;

import com.hajdu.sp.common.Interval;
import lombok.*;
import com.hajdu.sp.competition.lib.value.StageId;
import com.hajdu.sp.competition.lib.value.rule.StageRuleSet;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
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


