package value.competition.season;

import java.util.*;

import com.sp.core.query.configurations.Interval;
import lombok.*;
import value.competition.rule.StageRuleSet;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class Stage {

	private int index;

	@NonNull
	private String name;

	@NonNull
	private StageRuleSet stageRuleSet;

	private List<Turn> turns = new ArrayList<>();

	@Singular
	private Set<String> clubIds;

	private Interval interval;

	public static StageBuilder builder(String name, int index) {
		return hiddenBuilder()
                .name(name)
                .index(index);
	}
}
