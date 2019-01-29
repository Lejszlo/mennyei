package com.hajdu.sp.match.query.service;

import com.hajdu.sp.match.lib.resource.MatchDetailsDocumentResource;
import com.hajdu.sp.match.query.entity.MatchDocument;
import com.hajdu.sp.match.query.repository.MatchDocumentMongoRepository;
import com.hajdu.sp.match.query.resource.MatchDetailsDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatchDetailsService {

    @Autowired
    private MatchDocumentMongoRepository matchDocumentMongoRepository;

    public MatchDetailsDocumentResource getPlayerMatchDetails(String matchId) {
        MatchDocument matchDocument = matchDocumentMongoRepository.findById(matchId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, matchId));
        MatchDetailsDocumentResourceAssemblerSupport matchResourceDetailsAssemblerSupport = new MatchDetailsDocumentResourceAssemblerSupport();
        return matchResourceDetailsAssemblerSupport.toResource(matchDocument);
    }
}
