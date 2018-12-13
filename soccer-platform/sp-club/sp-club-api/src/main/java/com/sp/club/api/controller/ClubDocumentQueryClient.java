package com.sp.club.api.controller;

import com.sp.club.api.resource.ClubDocumentResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ClubDocumentQueryClient {

    @GetMapping(value = "/api/club/{clubIds}")
    List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds);

}
