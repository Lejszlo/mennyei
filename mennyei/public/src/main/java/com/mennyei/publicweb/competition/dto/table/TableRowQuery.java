package com.mennyei.publicweb.competition.dto.table;

import com.mennyei.core.competition.domain.rule.CompetitionRuleSet;
import com.mennyei.core.match.domain.MatchResult;

import lombok.Getter;

public class TableRowQuery implements Comparable<TableRowQuery> {
	@Getter
	private String clubId;
	
	@Getter
	private String clubName;
	
	@Getter
	private int playedMatches;
	
	@Getter
	private int win;
	
	@Getter
	private int draw;
	
	@Getter
	private int lose;

	@Getter
	private int scoredGoals;
	
	@Getter
	private int concerdGoals;
	
	@Getter
	private int point;
	
	public TableRowQuery(String clubId, String clubName) {
		this.clubId = clubId;
		this.clubName = clubName;
	}
	
	public int incraseMatches() {
		return ++playedMatches;
	}
	
	public int addScoredGoals(int goals) {
		return scoredGoals += goals;
	}
	
	public int addConcerdGoals(int goals) {
		return concerdGoals += goals;
	}
	
	public void setResult(MatchResult MatchResult) {
		switch (MatchResult) {
		case DRAW:
			++draw;
			break;
		case LOSE:
			++lose;
			break;
		case WIN:
			++win;
			break;
		default:
			break;
		}
	}
	
	public int calculatePoints(CompetitionRuleSet competitionRuleSet) {
		point = win * competitionRuleSet.getPointsForWin();
		point += draw * competitionRuleSet.getPointsForDraw();
		point += lose * competitionRuleSet.getPointsForLose();
		return point;
	}
	
	@Override
	public int compareTo(TableRowQuery tableRowQuery) {
		return 0;
	}

}
