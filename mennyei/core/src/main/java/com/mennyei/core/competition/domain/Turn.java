package com.mennyei.core.competition.domain;

import java.util.Set;

import com.mennyei.core.match.domain.Match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Turn {
	private Integer number;
	
	private Set<Match> matches;
}
