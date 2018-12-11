package com.sp.organizer.api.command.competition;

import com.sp.core.query.configurations.Interval;
import com.sp.organizer.api.value.competition.StageId;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder
@AllArgsConstructor
public class AddStage extends CompetitionCommand {

    @NotNull
    private StageId stageId;

    @NonNull
    private String name;

    @NonNull
    private StageRuleSet stageRuleSet;

    @NonNull
    private Interval interval;
}


