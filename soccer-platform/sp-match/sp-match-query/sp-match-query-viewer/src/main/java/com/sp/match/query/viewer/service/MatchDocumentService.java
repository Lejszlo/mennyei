package com.sp.match.query.viewer.service;

import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;
import com.sp.organizer.api.value.club.ClubId;
import com.sp.organizer.api.value.club.ClubInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.resource.MatchResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class MatchDocumentService {
    private final MatchDocumentMongoRepository matchMongoRepository;
    private final MatchResourceAssemblerSupport matchResourceAssemblerSupport;

    @Autowired
    public MatchDocumentService(MatchDocumentMongoRepository matchMongoRepository, MatchResourceAssemblerSupport matchResourceAssemblerSupport) {
        this.matchMongoRepository = matchMongoRepository;
        this.matchResourceAssemblerSupport = matchResourceAssemblerSupport;
    }

    public MatchDocumentResource getMatch(String matchId) {
        MatchDocument matchDocument = matchMongoRepository.findOne(matchId);
        return matchResourceAssemblerSupport.toResource(matchDocument);
    }

    public Resources<MatchDocumentResource> getMatches(List<String> matchIds) {
        List<MatchDocument> matches = matchMongoRepository.findByIdIn(matchIds);
//        List<ClubId> clubs = new ArrayList<>();
//        matches.forEach(matchDocument -> {
//            clubs.add(matchDocument.getAwayClubId());
//            clubs.add(matchDocument.getHomeClubId());
//        });
        return new Resources<>(matchResourceAssemblerSupport.toResources(matches));
    }

    public Resources<MatchDocumentResource> getMatchByClub(String clubId) {
        List<MatchDocument> matches = matchMongoRepository.findByClubOrderByMatchDate(clubId);
        return new Resources<>( matchResourceAssemblerSupport.toResources(matches));
    }
}
