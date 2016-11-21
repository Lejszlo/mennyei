package com.mennyei.core.competition.domain;

import com.mennyei.core.match.domain.Match;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class Turn {
	private Integer number;
	
	private boolean played;
	
	private Set<Match> matches;
}
