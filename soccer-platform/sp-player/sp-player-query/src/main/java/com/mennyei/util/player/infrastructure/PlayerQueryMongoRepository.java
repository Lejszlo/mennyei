package com.mennyei.util.player.infrastructure;

import com.mennyei.util.player.domain.PlayerQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@RepositoryRestResource(path="players")
public interface PlayerQueryMongoRepository extends MongoRepository<PlayerQuery, String> {

    List<PlayerQuery> findByClubId(String clubId);
}
