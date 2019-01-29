package com.hajdu.sp.match.query.controller;

import com.hajdu.sp.match.lib.controller.MatchDocumentQueryController;
import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import com.hajdu.sp.match.query.service.MatchDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchDocumentQueryClient implements MatchDocumentQueryController {
	private final MatchDocumentService matchDocumentService;

	@Autowired
    public MatchDocumentQueryClient(MatchDocumentService matchDocumentService) {
        this.matchDocumentService = matchDocumentService;
    }

    @Override
    @RequestMapping(value = "/noclubs/{matchIds}")
    public List<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds) {
        return matchDocumentService.getMatches(matchIds);
    }

    @GetMapping(value = "/generate")
    public List<MatchDocumentResource> getMatches() {
        return null;
    }


}
