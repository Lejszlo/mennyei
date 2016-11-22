package com.mennyei.core.transfer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class Transfer {

	private LocalDate transferDate;
	
	private String sourceTeamId;
	
	private String targetTeamId;

	private String playerId;
}
