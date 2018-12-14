package sp.competition.api.command;

import com.sp.match.api.value.MatchId;
import lombok.*;
import sp.common.Interval;
import sp.competition.api.value.TurnId;
import sp.competition.api.value.season.Turn;

import java.util.Collection;
import java.util.List;

@Value
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class AddMatches extends CompetitionCommand {
	@NonNull
	TurnId turnId;

	@Singular
	Collection<MatchId> matches;

	Interval interval;
}
