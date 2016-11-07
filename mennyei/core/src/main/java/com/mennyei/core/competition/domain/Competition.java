package com.mennyei.core.competition.domain;

import java.util.Set;

import com.mennyei.core.team.domain.Team;

import lombok.Data;

@Data
public class Competition {
	
	private Fixtures fixtures;
	
	private String name;
	
	private Set<Team> teams;

	public Competition save(Long Id) {
		return null;
	}
	
	public Competition findById(Long Id) {
		return null;
	}
	
}
