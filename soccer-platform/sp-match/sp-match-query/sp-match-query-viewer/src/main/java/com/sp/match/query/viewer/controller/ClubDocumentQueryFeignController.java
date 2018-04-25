package com.sp.match.query.viewer.controller;

import com.sp.organizer.api.controller.ClubQueryClient;
import com.sp.organizer.api.resource.ClubDocumentResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("organizer-query")
public interface ClubDocumentQueryFeignController extends ClubQueryClient {

    @Override
    @RequestMapping(value = "/club/{clubIds}", method = RequestMethod.GET)
    List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds);
}
