package com.mennyei.core.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Transfer {

	private String transferDate;
	
	private String sourceTeamId;
	
	private String targetTeamId;

	private String playerId;
}
