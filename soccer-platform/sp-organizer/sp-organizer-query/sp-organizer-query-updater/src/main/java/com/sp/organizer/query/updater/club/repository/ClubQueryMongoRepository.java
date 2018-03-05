package com.sp.organizer.query.updater.club.repository;

import com.sp.organizer.query.updater.club.entity.ClubDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubQueryMongoRepository extends MongoRepository<ClubDocument, String> {
    ClubDocument findByUrlName(@Param("urlName") String urlName);
}
