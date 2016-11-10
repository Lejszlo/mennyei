package com.mennyei.core.competition.domain;

import java.util.Set;

import com.mennyei.core.match.domain.Match;

import lombok.Data;

@Data
public class Turn {
	private Integer number;
	
	private boolean played;
	
	private Set<Match> matches;
}
