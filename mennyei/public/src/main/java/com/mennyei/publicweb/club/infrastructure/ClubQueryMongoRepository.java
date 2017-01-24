package com.mennyei.publicweb.club.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mennyei.publicweb.club.dto.ClubQuery;

/**
 * Created by lejsz on 2016. 11. 21..
 */
public interface ClubQueryMongoRepository extends MongoRepository<ClubQuery, String> {
    ClubQuery findByUrlName(String urlName);
}
