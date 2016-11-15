package com.mennyei.core.player.domain;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private Integer number;
	
	private LocalDate birthday;

}
