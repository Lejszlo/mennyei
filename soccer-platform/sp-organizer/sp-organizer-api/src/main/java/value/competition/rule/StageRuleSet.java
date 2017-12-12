package value.competition.rule;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class StageRuleSet {

	private int numberOfTeams;
	
	private int pointsForWin = 3;
	
	private int pointsForDraw = 1;
	
	private int pointsForLose = 0;
	
	private int numberOfMatches;
	
	private List<SortingRule> sortingRules;
	
	private int subsNamed;
	
	private int subsUsed;
	
	private int relegation;
	
	private int promotion;
	
	private int yellowCardLimit;
	
	private int yellowCardsBan = 1;
	
	private int redCardsBan = 1;
	
	private int oneHalfMinutes = 45;
	
	private boolean hasOverTime;
	
	private int numberOfHalfs = 2;
	
	private int oneHalfOverTimeMinutes = 15;
	
	private boolean hasPenalties;
	
}
