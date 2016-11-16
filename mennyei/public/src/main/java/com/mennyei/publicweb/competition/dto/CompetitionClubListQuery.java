package com.mennyei.publicweb.competition.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Value;

@Document
@Builder
@Value
public class CompetitionClubListQuery {

	private Long id;
	
	private String name;
	
	private Set<String> clubIds = new HashSet<>();
}
