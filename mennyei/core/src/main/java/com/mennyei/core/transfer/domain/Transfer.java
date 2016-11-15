package com.mennyei.core.transfer.domain;

import java.time.LocalDate;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.domain.Club;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {
	
	private Long id;
	
	private LocalDate transferDate;
	
	private Club sourceTeam;
	
	private Club targetTeam;

	private Player player;
}
