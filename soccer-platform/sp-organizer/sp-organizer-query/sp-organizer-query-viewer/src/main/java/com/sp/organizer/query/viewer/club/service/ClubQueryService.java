package com.sp.organizer.query.viewer.club.service;

import com.sp.organizer.query.updater.club.entity.ClubDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sp.organizer.query.updater.club.repository.ClubQueryMongoRepository;

import java.util.Optional;

@Service
public class ClubQueryService {

    @Autowired
    public ClubQueryMongoRepository clubQueryMongoRepository;

    public Optional<ClubDocument> findClubByUrlName(String urlName) {
        return Optional.ofNullable(clubQueryMongoRepository.findByUrlName(urlName));
    }

    public Optional<ClubDocument> findClubById(String clubId) {
        return Optional.ofNullable(clubQueryMongoRepository.findOne(clubId));
    }


}
