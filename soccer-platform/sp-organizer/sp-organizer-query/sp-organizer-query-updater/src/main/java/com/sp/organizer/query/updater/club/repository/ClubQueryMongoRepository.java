package com.sp.organizer.query.updater.club.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import com.sp.organizer.query.updater.club.entity.ClubQuery;

public interface ClubQueryMongoRepository extends MongoRepository<ClubQuery, String> {
    ClubQuery findByUrlName(@Param("urlName") String urlName);
}
