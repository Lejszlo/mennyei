package com.mennyei.core.player.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Player {
	
	private String name;
	
	private Integer number;

	private String birthday;

}
