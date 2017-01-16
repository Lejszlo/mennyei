package com.mennyei.core.match.domain;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "hiddenBuilder")
public class Result {

	@Getter
	private int homeGoalAmount;

	@Getter
	private int awayGoalAmount;

	public MatchResultType whoIsTheWinner() {
		int result = homeGoalAmount - awayGoalAmount;
		if (result > 0) {
			return MatchResultType.HOME;
		}
		if (result < 0) {
			return MatchResultType.AWAY;
		}
		return MatchResultType.DRAW;
	}

	public static ResultBuilder builder(int homeGoalAmount, int awayGoalAmount) {
		return hiddenBuilder().awayGoalAmount(awayGoalAmount).homeGoalAmount(homeGoalAmount);
	}

}
