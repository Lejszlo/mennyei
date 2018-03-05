package com.sp.match.api.controller;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import com.sp.match.api.resource.MatchDetailsDocumentResource;
import com.sp.match.api.resource.MatchDocumentResource;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public interface MatchDocumentControllerApi {

    @GetMapping(value = "/{matchIds}")
    Resources<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds);

    @GetMapping(value = "/{matchId}/details")
    MatchDetailsDocumentResource getMatchByClubWithDetails(@PathVariable("matchId") String matchId);

}

