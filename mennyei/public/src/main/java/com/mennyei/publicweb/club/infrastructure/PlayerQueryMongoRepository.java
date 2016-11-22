package com.mennyei.publicweb.club.infrastructure;

import com.mennyei.publicweb.club.dto.PlayerQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lejsz on 2016. 11. 22..
 */
public interface PlayerQueryMongoRepository extends MongoRepository<PlayerQuery, String> {
}
