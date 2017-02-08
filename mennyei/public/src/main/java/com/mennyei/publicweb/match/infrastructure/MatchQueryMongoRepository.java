package com.mennyei.publicweb.match.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mennyei.publicweb.match.dto.MatchQuery;

@RepositoryRestResource(path="mathces")
public interface MatchQueryMongoRepository extends MongoRepository<MatchQuery, String> {
	
	@Query("{ $or: [ {'homeClub' : ?0}, {'awayClub' : ?0} ]}")
	List<MatchQuery> findByClub(@Param("clubId") String clubId);

}
