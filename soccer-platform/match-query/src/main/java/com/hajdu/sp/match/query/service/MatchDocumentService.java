package com.hajdu.sp.match.query.service;

import com.hajdu.sp.match.lib.resource.MatchDocumentResource;
import com.hajdu.sp.match.query.entity.MatchDocument;
import com.hajdu.sp.match.query.repository.MatchDocumentMongoRepository;
import com.hajdu.sp.match.query.resource.MatchResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatchDocumentService {
    private final MatchDocumentMongoRepository matchMongoRepository;
    private final MatchResourceAssemblerSupport matchResourceAssemblerSupport;

    @Autowired
    public MatchDocumentService(MatchDocumentMongoRepository matchMongoRepository,
                                MatchResourceAssemblerSupport matchResourceAssemblerSupport) {
        this.matchMongoRepository = matchMongoRepository;
        this.matchResourceAssemblerSupport = matchResourceAssemblerSupport;
    }

    public MatchDocumentResource getMatch(String matchId) {
        MatchDocument matchDocument = matchMongoRepository.findById(matchId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, matchId));
        return matchResourceAssemblerSupport.toResource(matchDocument);
    }

    public List<MatchDocumentResource> getMatches(List<String> matchIds) {
        List<MatchDocument> matches = matchMongoRepository.findByIdIn(matchIds);
        return matchResourceAssemblerSupport.toResources(matches);
    }
}
