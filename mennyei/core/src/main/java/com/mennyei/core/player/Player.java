package com.mennyei.core.player;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	
	private Long id;

	private String name;
	
	private LocalDate birthday;
	
}
