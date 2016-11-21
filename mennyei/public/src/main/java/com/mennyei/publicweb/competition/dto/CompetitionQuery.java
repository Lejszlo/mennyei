package com.mennyei.publicweb.competition.dto;

import com.mennyei.publicweb.club.dto.ClubQuery;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document
@Builder
@Value
public class CompetitionQuery {

	@Id
	private String id;
	
	private String name;

	@DBRef
	private Set<ClubQuery> clubs = new HashSet<>();
}
