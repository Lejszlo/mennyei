package com.mennyei.publicweb.competition.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mennyei.publicweb.competition.dto.table.TableQuery;

public interface TableMongoRepository extends MongoRepository<TableQuery, String> {

	TableQuery findByCompetitionIdAndStageName(String id, String stageName);

}
