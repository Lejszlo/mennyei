package com.sp.organizer.backend.match.service;

import com.sp.organizer.backend.match.dto.match.MatchQuery;
import com.sp.organizer.backend.match.infrastructure.MatchQueryMongoRepository;
import com.sp.organizer.backend.match.resource.MatchDetailesResource;
import com.sp.organizer.backend.match.resource.MatchDetailesResourceAssemblerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lejsz on 2017. 05. 08..
 */

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
