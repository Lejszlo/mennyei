package com.mennyei.publicweb.club.service;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.infrastructure.ClubQueryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by lejsz on 2017. 04. 21..
 */
@Service
public class ClubQueryService {

    public ClubQueryMongoRepository clubQueryMongoRepository;

    @Autowired
    public ClubQueryService(ClubQueryMongoRepository clubQueryMongoRepository) {
        this.clubQueryMongoRepository = clubQueryMongoRepository;
    }

    public Optional<ClubQuery> findClubByUrlName(String urlName) {
        return Optional.ofNullable(clubQueryMongoRepository.findByUrlName(urlName));
    }

    public Optional<ClubQuery> findClubById(String clubId) {
        return Optional.ofNullable(clubQueryMongoRepository.findOne(clubId));
    }


}
