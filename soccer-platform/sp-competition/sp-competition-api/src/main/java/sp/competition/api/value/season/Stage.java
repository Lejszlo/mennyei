package sp.competition.api.value.season;

import com.sp.club.api.value.ClubId;
import lombok.*;
import sp.common.Interval;
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
	private Set<ClubId> clubIds;

	@NonNull
	private Interval interval;

}
