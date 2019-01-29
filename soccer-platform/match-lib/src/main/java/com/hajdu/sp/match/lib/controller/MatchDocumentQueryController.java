package com.hajdu.sp.match.lib.controller;

import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MatchDocumentQueryController {

    @GetMapping(value = "/api/matches/noclubs/{matchIds}")
    List<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds);

}