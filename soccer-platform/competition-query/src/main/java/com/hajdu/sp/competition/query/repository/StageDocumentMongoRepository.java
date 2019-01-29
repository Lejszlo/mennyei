package com.hajdu.sp.competition.query.repository;

import com.hajdu.sp.competition.query.entity.StageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDocumentMongoRepository extends MongoRepository<StageDocument, String>  {

    @Query("{ $and: [ {'competitionDocumentId' : ?0}, {'id' : ?1} ]}")
    StageDocument findByCompetitionIdAndStageIndex(@Param("competitionDocumentId") String competitionId, @Param("id") String stageId);

}
