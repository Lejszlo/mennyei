package sp.competition.api.value.season;

import com.sp.core.query.configurations.Interval;
import lombok.*;
import sp.competition.api.value.StageId;
import sp.competition.api.value.rule.StageRuleSet;

import java.util.List;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class Stage {

	@NonNull
	private StageId id;

	@NonNull
	private String name;

	@NonNull
	private StageRuleSet stageRuleSet;

	@Singular
	private List<Turn> turns;

	@Singular
	private Set<String> clubIds;

	@NonNull
	private Interval interval;

}
