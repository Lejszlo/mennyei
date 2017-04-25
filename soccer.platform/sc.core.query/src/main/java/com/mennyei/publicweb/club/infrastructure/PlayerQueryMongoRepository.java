package com.mennyei.publicweb.club.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mennyei.publicweb.club.dto.PlayerQuery;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@RepositoryRestResource(path="players")
public interface PlayerQueryMongoRepository extends MongoRepository<PlayerQuery, String> {
}
