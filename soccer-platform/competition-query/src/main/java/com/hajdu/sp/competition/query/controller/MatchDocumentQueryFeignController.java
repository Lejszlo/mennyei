package com.hajdu.sp.competition.query.controller;

import com.hajdu.sp.match.lib.controller.MatchDocumentQueryController;
import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("match-query")
public interface MatchDocumentQueryFeignController extends MatchDocumentQueryController {

    @Override
    @RequestMapping(value = "/api/matches/noclubs/{matchIds}", method = RequestMethod.GET)
    List<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds);
}
