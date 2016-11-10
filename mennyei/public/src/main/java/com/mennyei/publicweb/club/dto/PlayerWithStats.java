package com.mennyei.publicweb.club.dto;

import com.mennyei.core.player.domain.Player;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerWithStats {
	
	private Player player;
	
	private int age;
	
	private PlayerMatchStatisticData matchStatisticData;
}
