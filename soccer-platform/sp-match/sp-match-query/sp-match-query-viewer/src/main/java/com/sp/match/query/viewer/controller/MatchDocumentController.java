package com.sp.match.query.viewer.controller;

import com.sp.match.query.viewer.service.MatchDetailsService;
import com.sp.match.query.viewer.service.MatchDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import com.sp.match.api.controller.MatchDocumentControllerApi;
import com.sp.match.api.resource.MatchDetailsDocumentResource;
import com.sp.match.api.resource.MatchDocumentResource;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchDocumentController implements MatchDocumentControllerApi {
	private final MatchDetailsService matchDetailsService;
	private final MatchDocumentService matchDocumentService;

	@Autowired
    public MatchDocumentController(MatchDetailsService matchDetailsService,
                                   MatchDocumentService matchDocumentService) {
        this.matchDetailsService = matchDetailsService;
        this.matchDocumentService = matchDocumentService;
    }

    @Override
    @GetMapping(value = "/{matchIds}")
    public Resources<MatchDocumentResource> getMatches(@PathVariable List<String> matchIds) {
        return matchDocumentService.getMatches(matchIds);
    }

    @Override
	@GetMapping(value = "/{matchId}/details")
	public MatchDetailsDocumentResource getMatchByClubWithDetails(@PathVariable String matchId) {
        return matchDetailsService.getPlayerMatchDetails(matchId);
	}

}
