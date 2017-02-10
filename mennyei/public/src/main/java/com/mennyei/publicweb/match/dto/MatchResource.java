package com.mennyei.publicweb.match.dto;

import org.springframework.hateoas.ResourceSupport;

import com.mennyei.core.match.domain.MatchResult;

import lombok.Data;

@Data
public class MatchResource extends ResourceSupport {

	private MatchQuery matchQuery;
	
	private MatchResult matchResult;
	
	private boolean atHome;
	
}