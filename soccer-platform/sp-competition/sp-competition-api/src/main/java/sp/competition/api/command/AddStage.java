package sp.competition.api.command;

import lombok.*;
import sp.common.Interval;
import sp.competition.api.value.StageId;
import sp.competition.api.value.rule.StageRuleSet;

import javax.validation.constraints.NotNull;

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


