package com.mennyei.publicweb.club.dto;

import com.mennyei.core.player.domain.Player;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerWithStats {
	
	private Player player;
	
	private int age;
	
	private int yellowCardAmount;
	
	private int redCardAmount;
	
	private int scoredGoalAmount;
	
	private int playedMinute;
	
	private int playedMatches;
	
	private int substitutionIn;
	
	private int substitutionOut;
	
	private int starter;
	
}
