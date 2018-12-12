package com.sp.match.query.viewer.controller;

import com.sp.match.api.controller.MatchDocumentQueryController;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.service.MatchDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
