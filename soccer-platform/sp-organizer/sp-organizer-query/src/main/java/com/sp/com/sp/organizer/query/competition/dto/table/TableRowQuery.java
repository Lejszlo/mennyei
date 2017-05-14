package com.sp.organizer.backend.competition.dto.table;

import com.sp.core.backend.value.match.MatchResult;
import com.sp.organizer.backend.competition.domain.rule.CompetitionRuleSet;
import org.springframework.data.mongodb.core.mapping.Document;

import com.sp.organizer.backend.club.domain.ClubQuery;

import lombok.Getter;

@Document
public class TableRowQuery implements Comparable<TableRowQuery> {
	
	@Getter
	private ClubQuery club;
	
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
	
	public TableRowQuery(ClubQuery club) {
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
