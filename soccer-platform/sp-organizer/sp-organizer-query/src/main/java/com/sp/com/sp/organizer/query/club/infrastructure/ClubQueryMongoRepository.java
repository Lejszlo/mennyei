package com.sp.organizer.backend.club.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sp.organizer.backend.club.domain.ClubQuery;

/**
 * Created by lejsz on 2016. 11. 21..
 */
@RepositoryRestResource(path="clubs")
public interface ClubQueryMongoRepository extends MongoRepository<ClubQuery, String> {
    ClubQuery findByUrlName(@Param("urlName") String urlName);
}
