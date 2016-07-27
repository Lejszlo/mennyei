package core.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import core.domain.entity.Member;

public interface MemberRepository extends GraphRepository<Member> {

}
