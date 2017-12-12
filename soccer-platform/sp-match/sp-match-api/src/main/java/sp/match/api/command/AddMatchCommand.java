package sp.match.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import sp.match.api.value.MatchInfo;

@Value
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddMatchCommand extends MatchCommand {
	
	private MatchInfo matchInfo;
	
	public static AddMatchCommandBuilder builder(MatchInfo matchInfo) {
		return hiddenBuilder().matchInfo(matchInfo);
	}
}
