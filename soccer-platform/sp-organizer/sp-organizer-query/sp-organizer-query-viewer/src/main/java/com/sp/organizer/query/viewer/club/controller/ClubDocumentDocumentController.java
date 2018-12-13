package com.sp.organizer.query.viewer.club.controller;

import com.sp.club.api.controller.ClubDocumentQueryClient;
import com.sp.club.api.resource.ClubDocumentResource;
import com.sp.organizer.query.viewer.club.service.ClubDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/club/")
public class ClubDocumentDocumentController implements ClubDocumentQueryClient {
	private final ClubDocumentService clubDocumentService;

	@Autowired
	public ClubDocumentDocumentController(ClubDocumentService clubDocumentService) {
		this.clubDocumentService = clubDocumentService;
    }
	
	@Override
	@GetMapping(value = "/{clubIds}")
	public List<ClubDocumentResource> getClubs(@PathVariable("clubIds") List<String> clubIds) {
		return clubDocumentService.findClubs(clubIds);
	}
}
