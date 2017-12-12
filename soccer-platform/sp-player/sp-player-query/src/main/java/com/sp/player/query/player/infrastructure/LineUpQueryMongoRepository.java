package com.sp.player.query.player.infrastructure;

import com.sp.player.query.player.domain.lineup.LineUpQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@RepositoryRestResource(path="lineup")
public interface LineUpQueryMongoRepository extends MongoRepository<LineUpQuery, String> {

    List<LineUpQuery> findByMatchId(String clubId);
}
