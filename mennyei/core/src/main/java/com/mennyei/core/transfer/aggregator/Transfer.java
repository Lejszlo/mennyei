package com.mennyei.core.transfer.aggregator;

import java.time.LocalDate;

import com.mennyei.core.player.Player;
import com.mennyei.core.team.domain.Team;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {
	private LocalDate transferDate;
	
	private Team sourceTeam;
	
	private Team targetTeam;

	private Player player;
}
