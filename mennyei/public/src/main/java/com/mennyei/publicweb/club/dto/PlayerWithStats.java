package com.mennyei.publicweb.club.dto;

import org.springframework.hateoas.ResourceSupport;

import com.mennyei.core.player.domain.Player;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerWithStats extends ResourceSupport {
	
	private Player player;
	
	private int age;
	
	private PlayerMatchStatisticData matchStatisticData;
}
