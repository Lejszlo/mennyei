package com.sp.organizer.query.updater.competition.entity;

import com.sp.organizer.query.updater.club.entity.ClubDocument;
import com.sp.match.api.value.MatchResult;
import com.sp.organizer.api.value.competition.rule.StageRuleSet;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Document
public class TableRowQuery implements Comparable<TableRowQuery> {
	
	@Getter
	private ClubDocument club;
	
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
	
	public TableRowQuery(ClubDocument club) {
		this.club = club;
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
	
	public int calculatePoints(StageRuleSet stageRuleSet) {
		point = win * stageRuleSet.getPointsForWin();
		point += draw * stageRuleSet.getPointsForDraw();
		point += lose * stageRuleSet.getPointsForLose();
		return point;
	}
	
	@Override
	public int compareTo(TableRowQuery tableRowQuery) {
		return 0;
	}

}
