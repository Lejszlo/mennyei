package com.mennyei.publicweb.competition.dto.table;

import com.mennyei.core.competition.domain.match.domain.MatchResult;
import com.mennyei.core.competition.domain.rule.CompetitionRules;

public class TableRow {
	private int place;
	
	private String clubId;
	
	private int playedMatches;
	
	private int win;
	
	private int draw;
	
	private int lose;

	private int scoredGoals;
	
	private int concerdGoals;
	
	private int point;
	
	public TableRow(String clubId) {
		this.clubId = clubId;
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
	
	public int calculatePoints(CompetitionRules competitionRules) {
		point = win * competitionRules.getPointsForWin();
		point += draw * competitionRules.getPointsForDraw();
		point += lose * competitionRules.getPointsForLose();
		return point;
	}
	
	public void setPlace(int place) {
		this.place = place;
	}
	
	public int getPlace() {
		return place;
	}
	
	public String getClubId() {
		return clubId;
	}
}
