package com.sp.organizer.query.updater.match.repository;

import com.sp.organizer.query.updater.match.entity.MatchQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchQueryMongoRepository extends MongoRepository<MatchQuery, String> {

	@Query("{ $or: [ {'homeClub.$id' : ?0}, {'awayClub.$id' : ?0} ]}")
	List<MatchQuery> findByClubOrderByMatchDate(@Param("clubId") String clubId);

}
