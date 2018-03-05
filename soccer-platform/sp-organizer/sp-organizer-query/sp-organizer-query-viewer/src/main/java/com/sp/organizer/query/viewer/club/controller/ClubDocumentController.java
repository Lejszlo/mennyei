package com.sp.organizer.query.viewer.club.controller;

import com.sp.organizer.api.controller.ClubQueryClient;
import com.sp.organizer.api.resource.ClubDocumentResource;
import com.sp.organizer.query.viewer.club.resource.ClubDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;
import com.sp.organizer.query.viewer.club.service.ClubDocumentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/club/")
public class ClubDocumentController implements ClubQueryClient {
	private final ClubDocumentService clubDocumentService;

	@Autowired
	public ClubDocumentController(ClubDocumentService clubDocumentService) {
		this.clubDocumentService = clubDocumentService;
    }
	
	@Override
	@GetMapping(value = "/{clubIds}")
	public List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds) {
		return clubDocumentService.findClubs(clubIds);
	}
}
