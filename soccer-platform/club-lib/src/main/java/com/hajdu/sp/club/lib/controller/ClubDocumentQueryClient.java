package com.hajdu.sp.club.lib.controller;

import com.hajdu.sp.club.lib.resource.ClubDocumentResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClubDocumentQueryClient {

    @GetMapping(value = "/api/club/{clubIds}")
    List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds);

}
