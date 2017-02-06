package com.mennyei.publicweb.match.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mennyei.publicweb.match.dto.MatchQuery;
import com.mennyei.publicweb.match.infrastructure.MatchMongoRepository;

@Controller
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchMongoRepository matchMongoRepository;
	
	@RequestMapping(value = "/{club}", method = RequestMethod.GET)
	public List<MatchQuery> byClub(@PathVariable String clubId) {  
		return matchMongoRepository.findByClub(clubId);
	}
	
}
