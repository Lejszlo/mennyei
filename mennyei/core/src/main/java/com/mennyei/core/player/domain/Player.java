package com.mennyei.core.player.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	
	private Long id;

	private String name;
	
	private Integer number;
	
	private LocalDate birthday;

}
