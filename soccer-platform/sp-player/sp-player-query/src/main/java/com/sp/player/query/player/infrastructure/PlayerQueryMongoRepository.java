package com.sp.player.query.player.infrastructure;

import com.sp.player.query.player.domain.PlayerQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerQueryMongoRepository extends MongoRepository<PlayerQuery, String> {

    List<PlayerQuery> findByClubId(String clubId);
}
