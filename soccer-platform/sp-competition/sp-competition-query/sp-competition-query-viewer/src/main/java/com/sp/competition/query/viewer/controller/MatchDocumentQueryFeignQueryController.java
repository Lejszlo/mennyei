package com.sp.competition.query.viewer.controller;

import com.sp.match.api.controller.MatchDocumentQueryController;
import com.sp.match.api.resource.MatchDocumentResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("match-query")
public interface MatchDocumentQueryFeignQueryController extends MatchDocumentQueryController {

    @Override
    @RequestMapping(value = "/api/matches/noclubs/{matchIds}", method = RequestMethod.GET)
    List<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds);
}
