package com.mennyei.publicweb.competition.dto.table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
}
