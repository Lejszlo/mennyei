package com.hajdu.sp.competition.query.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompetitionNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CompetitionNotFoundException(String competitionId) {
		super("could not find com.sp.organizer.backend.competition '" + competitionId + "'.");
	}

}
