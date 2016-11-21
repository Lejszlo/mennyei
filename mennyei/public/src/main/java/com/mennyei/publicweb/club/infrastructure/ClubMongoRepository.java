package com.mennyei.publicweb.club.infrastructure;

import com.mennyei.publicweb.club.dto.ClubQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lejsz on 2016. 11. 21..
 */
public interface ClubMongoRepository extends MongoRepository<ClubQuery, String> {
}
