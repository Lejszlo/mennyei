package core.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import core.domain.entity.Modification;

public interface ModificationRepository extends GraphRepository<Modification> {

}
