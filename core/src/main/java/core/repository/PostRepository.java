package core.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import core.domain.entity.Post;

public interface PostRepository extends GraphRepository<Post> {

}
