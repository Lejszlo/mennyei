package com.sp.organizer.query.viewer.match.service;

import com.sp.organizer.query.viewer.match.resource.MatchDetailesResource;
import com.sp.organizer.query.viewer.match.resource.MatchDetailesResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.match.entity.MatchQuery;
import com.sp.organizer.query.updater.match.repository.MatchQueryMongoRepository;

@Service
public class MatchDetailesService {

    @Autowired
    private MatchQueryMongoRepository matchQueryMongoRepository;

    public MatchDetailesResource getPlayerMatchDetailes(String matchId) {
        MatchQuery matchQuery = matchQueryMongoRepository.findOne(matchId);
        MatchDetailesResourceAssemblerSupport matchResourceDetailesAssemblerSupport = new MatchDetailesResourceAssemblerSupport();
        MatchDetailesResource detailesResource = matchResourceDetailesAssemblerSupport.toResource(matchQuery);
        return detailesResource;
    }
}
