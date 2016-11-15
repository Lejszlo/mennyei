package com.mennyei.core.transfer.domain;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.domain.Club;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate transferDate;
	
	@ManyToOne
	private Club sourceTeam;
	
	@ManyToOne
	private Club targetTeam;

	@ManyToOne
	private Player player;
}
