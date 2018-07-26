package com.sp.organizer.query.viewer.competition.controller;

import com.sp.match.api.controller.MatchDocumentQueryController;
import com.sp.match.api.filter.MatchDocumentResourceFilter;
import com.sp.match.api.resource.MatchDocumentResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("match-query")
public interface MatchDocumentQueryFeignQueryController extends MatchDocumentQueryController {

    @Override
    @RequestMapping(value = "/api/matches/noclubs/{matchIds}", method = RequestMethod.GET)
    List<MatchDocumentResource> getMatches(@PathVariable("matchIds") List<String> matchIds);
}
