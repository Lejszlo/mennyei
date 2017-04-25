package com.mennyei.publicweb.match.infrastructure;

import com.mennyei.publicweb.match.dto.match.MatchQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="mathces")
public interface MatchQueryMongoRepository extends MongoRepository<MatchQuery, String> {

	@Query("{ $or: [ {'homeClub.$id' : ?0}, {'awayClub.$id' : ?0} ]}")
	List<MatchQuery> findByClubOrderByMatchDate(@Param("clubId") String clubId);

}
