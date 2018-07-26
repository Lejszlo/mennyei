package com.sp.match.query.updater.match.repository;

import com.sp.match.query.updater.match.entity.MatchDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchDocumentMongoRepository extends MongoRepository<MatchDocument, String> {

	@Query("{ $or: [ {'homeClub.$id' : ?0}, {'awayClub.$id' : ?0} ]}")
	List<MatchDocument> findByClubOrderByMatchDate(@Param("clubId") String clubId);

	List<MatchDocument> findByIdIn(List<String> matchIds);

	List<MatchDocument> findByPlayedIsAndIdIn(boolean played, List<String> matchIds);
}
