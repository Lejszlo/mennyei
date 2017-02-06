package com.mennyei.publicweb.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mennyei.publicweb.match.infrastructure.MatchMongoRepository;


@RequestMapping("/clubs/")
@RestController
public class ClubController {
	
	@Autowired
	private MatchMongoRepository matchMongoRepository;
	
	
}
