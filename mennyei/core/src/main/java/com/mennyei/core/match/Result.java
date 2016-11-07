package com.mennyei.core.match;

import lombok.Builder;
import lombok.Getter;

@Builder
public class Result {
	
	@Getter
	private Integer homeGoalAmount;
	
	@Getter
	private Integer awayGoalAmount;
	
	public MatchResultType whoIsTheWinner() {
		int result = homeGoalAmount - awayGoalAmount;
		if(result > 0) {
			return MatchResultType.HOME;
		}
		if(result < 0) {
			return MatchResultType.AWAY;
		}
		return MatchResultType.DRAW;
	}
	
}
