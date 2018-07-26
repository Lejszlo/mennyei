package com.sp.match.query.viewer.controller;

import com.sp.organizer.api.controller.ClubDocumentQueryClient;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("organizer-query")
public interface ClubDocumentQueryFeignController extends ClubDocumentQueryClient {

    @Override
    @RequestMapping(value = "/api/club/{clubIds}", method = RequestMethod.GET)
    List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds);
}
