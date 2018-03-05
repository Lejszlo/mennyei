package com.sp.match.query.viewer.service;

import com.sp.match.api.resource.MatchDetailsDocumentResource;
import com.sp.match.query.viewer.resource.MatchDetailsDocumentResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;

@Service
public class MatchDetailsService {

    @Autowired
    private MatchDocumentMongoRepository matchDocumentMongoRepository;

    public MatchDetailsDocumentResource getPlayerMatchDetails(String matchId) {
        MatchDocument matchDocument = matchDocumentMongoRepository.findOne(matchId);
        MatchDetailsDocumentResourceAssemblerSupport matchResourceDetailsAssemblerSupport = new MatchDetailsDocumentResourceAssemblerSupport();
        return matchResourceDetailsAssemblerSupport.toResource(matchDocument);
    }
}
