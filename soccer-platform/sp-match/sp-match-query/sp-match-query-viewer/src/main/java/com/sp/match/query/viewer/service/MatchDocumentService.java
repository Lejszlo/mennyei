package com.sp.match.query.viewer.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.sp.match.query.updater.match.entity.MatchDocument;
import com.sp.match.query.updater.match.repository.MatchDocumentMongoRepository;
import com.sp.organizer.api.controller.ClubQueryClient;
import com.sp.organizer.api.resource.ClubDocumentResource;
import com.sp.organizer.api.value.club.ClubId;
import org.apache.commons.collections.MultiMap;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import com.sp.match.api.resource.MatchDocumentResource;
import com.sp.match.query.viewer.resource.MatchResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class MatchDocumentService {
    private final MatchDocumentMongoRepository matchMongoRepository;
    private final MatchResourceAssemblerSupport matchResourceAssemblerSupport;
    private final ClubQueryClient clubQueryClient;

    @Autowired
    public MatchDocumentService(MatchDocumentMongoRepository matchMongoRepository,
                                MatchResourceAssemblerSupport matchResourceAssemblerSupport,
                                ClubQueryClient clubQueryClient) {
        this.matchMongoRepository = matchMongoRepository;
        this.matchResourceAssemblerSupport = matchResourceAssemblerSupport;
        this.clubQueryClient = clubQueryClient;
    }

    public MatchDocumentResource getMatch(String matchId) {
        MatchDocument matchDocument = matchMongoRepository.findOne(matchId);
        return matchResourceAssemblerSupport.toResource(matchDocument);
    }

    public Resources<MatchDocumentResource> getMatches(List<String> matchIds) {
        List<MatchDocument> matches = matchMongoRepository.findByIdIn(matchIds);
        return new Resources<>(matchResourceAssemblerSupport.toResourcesWithClubs(matches));
    }

    public Resources<MatchDocumentResource> getMatchByClub(String clubId) {
        List<MatchDocument> matches = matchMongoRepository.findByClubOrderByMatchDate(clubId);
        return new Resources<>( matchResourceAssemblerSupport.toResources(matches));
    }
}
