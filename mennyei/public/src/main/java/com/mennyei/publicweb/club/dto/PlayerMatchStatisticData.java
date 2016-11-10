package com.mennyei.publicweb.club.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerMatchStatisticData {
	
	private int yellowCardAmount;
	
	private int redCardAmount;
	
	private int scoredGoalAmount;
	
	private int playedMinute;
	
	private int substitutionIn;
	
	private int substitutionOut;
	
	private boolean starter;
}
