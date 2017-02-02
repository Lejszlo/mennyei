package com.mennyei.publicweb.club.infrastructure;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.club.dto.PlayerQuery;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@RepositoryRestResource(path="players")
public interface PlayerQueryMongoRepository extends MongoRepository<PlayerQuery, String> {
	Set<PlayerQuery> findByClub(@Param(value = "club") ClubQuery clubId);
}
