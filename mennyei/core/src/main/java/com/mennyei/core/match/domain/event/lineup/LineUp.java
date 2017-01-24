package com.mennyei.core.match.domain.event.lineup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName="hiddenBuilder")
@AllArgsConstructor
public class LineUp {

	private String playerId;
	
	private int shirtNumber;
	
	private LineUpType lineUpType;
	
	public static LineUpBuilder starter(String playerId, int shirtNumber) {
		return hiddenBuilder().playerId(playerId).shirtNumber(shirtNumber).lineUpType(LineUpType.STARTER);
	}
	
	public static LineUpBuilder substitution(String playerId, int shirtNumber) {
		return hiddenBuilder().playerId(playerId).shirtNumber(shirtNumber).lineUpType(LineUpType.SUBSTITUTION);
	}
}
