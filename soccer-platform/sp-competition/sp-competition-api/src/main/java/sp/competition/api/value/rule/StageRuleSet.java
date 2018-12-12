package sp.competition.api.value.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
public class StageRuleSet {

	int numberOfTeams;
	
	int pointsForWin = 3;
	
	int pointsForDraw = 1;
	
	int pointsForLose = 0;
	
	List<SortingRule> sortingRules;
	
//	int subsNamed;

//	int subsUsed;
	
	int relegation = 0;
	
	int promotion = 0;
	
	int yellowCardLimit = 0;
	
	int yellowCardsBan = 1;
	
	int redCardsBan = 1;
	
	int oneHalfMinutes = 45;

	int numberOfHalf = 2;

	// overtime
	int oneHalfOverTimeMinutes = 15;

	boolean hasOverTime = false;

	boolean hasPenalties = false;
	
}
