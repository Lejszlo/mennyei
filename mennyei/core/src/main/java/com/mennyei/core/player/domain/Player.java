package com.mennyei.core.player.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Player {
	
	private Long id;

	private String name;
	
	private Integer number;
	
	private LocalDate birthday;

}
