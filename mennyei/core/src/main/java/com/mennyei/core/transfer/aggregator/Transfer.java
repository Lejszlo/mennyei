package com.mennyei.core.transfer.aggregator;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mennyei.core.player.domain.Player;
import com.mennyei.core.team.domain.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private LocalDate transferDate;
	
	@ManyToOne
	private Team sourceTeam;
	
	@ManyToOne
	private Team targetTeam;

	@ManyToOne
	private Player player;
}
