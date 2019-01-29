package com.hajdu.sp.club.query;

import com.hajdu.sp.club.lib.controller.ClubDocumentQueryClient;
import com.hajdu.sp.club.lib.resource.ClubDocumentResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("organizer-query")
public interface ClubDocumentQueryFeignController extends ClubDocumentQueryClient {

    @Override
    @RequestMapping(value = "/api/club/{clubIds}", method = RequestMethod.GET)
    List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds);
}
