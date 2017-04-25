package com.mennyei.publicweb.competition.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mennyei.publicweb.club.dto.ClubQuery;
import com.mennyei.publicweb.competition.dto.competition.CompetitionQuery;

@RepositoryRestResource(path="competitions")
public interface CompetitionQueryMongoRepository extends MongoRepository<CompetitionQuery, String> {

	List<CompetitionQuery> findByClubsIn(ClubQuery clubQuery);
	
}
