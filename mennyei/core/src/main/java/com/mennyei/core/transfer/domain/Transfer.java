package com.mennyei.core.transfer.domain;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.domain.Club;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Transfer {
	
	private Long id;
	
	private LocalDate transferDate;
	
	private Club sourceTeam;
	
	private Club targetTeam;

	private Player player;
}
