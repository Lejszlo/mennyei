package com.mennyei.core.player.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Player {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private Integer number;
	
	private LocalDate birthday;

}
