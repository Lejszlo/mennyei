package com.mennyei.publicweb.match.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mennyei.publicweb.match.dto.MatchQuery;

@Repository
public interface MatchMongoRepository extends MongoRepository<MatchQuery, String> {
	@Query("{ $or: [ {'homeClub' : ?0}, {'awayClub' : ?0} ], $and: [{ 'competition.id' : ?1}] }")
	List<MatchQuery> findClubAndCompetition(@Param("clubId") String clubId,@Param("competitionId") String competitionId);

}
