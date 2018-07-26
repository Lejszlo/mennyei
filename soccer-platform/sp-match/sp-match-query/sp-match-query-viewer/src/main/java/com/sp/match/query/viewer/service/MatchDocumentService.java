package com.sp.match.query.viewer.service;

import com.sp.match.api.filter.MatchDocumentResourceFilter;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.resource.MatchResourceAssemblerSupport;

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
        MatchDocument matchDocument = matchMongoRepository.findOne(matchId);
        return matchResourceAssemblerSupport.toResource(matchDocument);
    }

    public List<MatchDocumentResource> getMatches(List<String> matchIds) {
        List<MatchDocument> matches = matchMongoRepository.findByIdIn(matchIds);
        return matchResourceAssemblerSupport.toResources(matches);
    }
}
