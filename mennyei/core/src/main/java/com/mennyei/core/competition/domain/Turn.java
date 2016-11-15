package com.mennyei.core.competition.domain;

import java.util.Set;

import com.mennyei.core.match.domain.Match;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Turn {
	private Integer number;
	
	private boolean played;
	
	private Set<Match> matches;
}
