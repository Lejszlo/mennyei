package com.mennyei.publicweb.club.dto;

import com.mennyei.core.player.domain.Player;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Builder
@Data
public class PlayerWithStats extends ResourceSupport {
	
	private Player player;
	
	private int age;
	
	private PlayerMatchStatisticData matchStatisticData;
}
