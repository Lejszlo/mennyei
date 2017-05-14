package com.sp.organizer.backend.competition.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sp.organizer.backend.club.domain.ClubQuery;
import com.sp.organizer.backend.competition.dto.competition.CompetitionQuery;

@RepositoryRestResource(path="competitions")
public interface CompetitionQueryMongoRepository extends MongoRepository<CompetitionQuery, String> {

	List<CompetitionQuery> findByClubsIn(ClubQuery clubQuery);
	
}
